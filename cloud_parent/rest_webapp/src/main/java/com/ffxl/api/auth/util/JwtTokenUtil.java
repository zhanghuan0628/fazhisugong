package com.ffxl.api.auth.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ffxl.api.config.JwtProperties;
import com.ffxl.platform.core.exception.BusinessException;
import com.ffxl.platform.core.support.HttpKit;
import com.ffxl.platform.util.HttpHeader;
import com.ffxl.platform.util.ToolUtil;

/**
 * <p>jwt token工具类</p>
 * <pre>
 *     jwt的claim里一般包含以下几种数据:
 *         1. iss -- token的发行者
 *         2. sub -- 该JWT所面向的用户
 *         3. aud -- 接收该JWT的一方
 *         4. exp -- token的失效时间
 *         5. nbf -- 在此时间段之前,不会被处理
 *         6. iat -- jwt发布时间
 *         7. jti -- jwt唯一标识,防止重复使用
 * </pre>
 * 
 * @author jiawei
 * 2018年7月2日
 */
@Component
public class JwtTokenUtil {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 获取用户名从token中
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token).getSubject();
    }

    /**
     * 获取jwt发布时间
     */
    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token).getIssuedAt();
    }

    /**
     * 获取jwt失效时间
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token).getExpiration();
    }

    /**
     * 获取jwt接收者
     */
    public String getAudienceFromToken(String token) {
        return getClaimFromToken(token).getAudience();
    }

    /**
     * 获取私有的jwt claim
     */
    public String getPrivateClaimFromToken(String token, String key) {
        try {
            return getClaimFromToken(token).get(key).toString();
        } catch (Exception e) {
            throw new BusinessException("HTTP请求时，header中【"+key+"】为空,后端却在使用，前后端协调头参数");
        }
       
        
    }

    /**
     * 获取md5 key从token中
     */
    public String getMd5KeyFromToken(String token) {
        return getPrivateClaimFromToken(token, jwtProperties.getMd5Key());
    }

    /**
     * 获取jwt的payload部分
     */
    public Claims getClaimFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 解析token是否正确,不正确会报异常<br>
     */
    public void parseToken(String token) throws JwtException {
        Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token).getBody();
    }

    /**
     * <pre>
     *  验证token是否失效
     *  true:过期   false:没过期
     * </pre>
     */
    public Boolean isTokenExpired(String token) {
        try {
            final Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        } catch (ExpiredJwtException expiredJwtException) {
            return true;
        }
    }

    /**
     * 生成token(通过用户名和签名时候用的随机数)
     */
    public String generateToken(String userName, String randomKey) {
        HttpHeader local= HttpHeader.get();
        Map<String, Object> claims = new HashMap<>();
        
        claims.put(HttpHeader.ACCEPT_LANGUAGE, local.getAcceptLanguage());
        claims.put(HttpHeader.DEVICE_MODEL, local.getDeviceModel());
        claims.put(HttpHeader.DEVICE_BRAND, local.getDeviceBrand());
        claims.put(HttpHeader.SYSTEM_TYPE, local.getSystemType());
        claims.put(HttpHeader.SYSTEM_VERSION, local.getSystemVersion());
        claims.put(HttpHeader.APP_TYPE, local.getAppType());
        claims.put(HttpHeader.APP_VERSION, local.getAppVersion());
        claims.put(HttpHeader.IP, HttpKit.getIp());
        claims.put(jwtProperties.getMd5Key(), randomKey);
        return doGenerateToken(claims, userName);
    }

    /**
     * 生成token
     */
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + jwtProperties.getExpiration() * 1000);

        return Jwts.builder()//返回的字符串便是我们的jwt串了
                .setClaims(claims)//Claims包含您想要签署的任何信息
                .setSubject(subject)//设置主题，可与业务系统唯一绑定
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())//设置算法（必须）
                .compact();//这个是全部设置完成后拼成jwt串的方法
    }

    /**
     * 获取混淆MD5签名用的随机字符串
     */
    public String getRandomKey() {
        return ToolUtil.getRandomString(6);
    }
}
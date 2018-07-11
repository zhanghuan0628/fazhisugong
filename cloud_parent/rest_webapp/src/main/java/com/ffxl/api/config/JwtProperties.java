package com.ffxl.api.config;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.context.annotation.Configuration;

/**
 * jwt相关配置
 * 
 * @author jiawei
 * 2018年7月2日
 */
@Configuration
public class JwtProperties {

    public static final String JWT_PREFIX = "jwt";

    private String header = "Authorization";

    private String secret = "defaultSecret";

    private Long expiration = 604800L;

    private String authPath = "auth";

    private String md5Key = "randomKey";
    
    private static final HashSet<String> no_auth = new HashSet<String>(Arrays.asList(new String[]{
            "/SgHomePageController/queryBanner",
            "/SgHomePageController/querySgLaw",
            "/SgHomePageController/querySgLawDetail",
            "/SgHomePageController/querySgMagic",
            "/SgHomePageController/querySgMagicDetail"
    }));

    public static String getJwtPrefix() {
        return JWT_PREFIX;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public String getAuthPath() {
        return authPath;
    }

    public void setAuthPath(String authPath) {
        this.authPath = authPath;
    }

    public String getMd5Key() {
        return md5Key;
    }

    public void setMd5Key(String md5Key) {
        this.md5Key = md5Key;
    }

    public static HashSet<String> getNoAuth() {
        return no_auth;
    }
    
}

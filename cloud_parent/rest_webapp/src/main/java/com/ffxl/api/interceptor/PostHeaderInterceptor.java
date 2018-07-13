package com.ffxl.api.interceptor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.JwtException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ffxl.api.auth.util.JwtTokenUtil;
import com.ffxl.api.config.JwtProperties;
import com.ffxl.cloud.model.SgUser;
import com.ffxl.platform.constant.JsonResult;
import com.ffxl.platform.constant.Message;
import com.ffxl.platform.core.support.HttpKit;
import com.ffxl.platform.util.DateUtil;
import com.ffxl.platform.util.HttpHeader;
import com.ffxl.platform.util.RenderUtil;

public class PostHeaderInterceptor extends HandlerInterceptorAdapter{
        private static final Logger logger = LoggerFactory.getLogger(PostHeaderInterceptor.class);
       
        @Autowired
        private JwtTokenUtil jwtTokenUtil;

        @Autowired
        private JwtProperties jwtProperties;
        
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            if (request.getMethod().equals("OPTIONS")) {
              //首次请求，放行
              logger.info("复杂跨域的试探OPTIONS请求放行》》》》》》》》》》");
              return true;
            }else{
                logger.info("开始执行Token拦截器------------------------");
                HttpHeader header = new HttpHeader();
                if (request.getServletPath().equals("/" + jwtProperties.getAuthPath())) {
                    // TODO 可在此处验证头参数
                    HttpHeader.set(header);
                    logger.info("未被拦截-----------1111》》》》》》》》》》"+request.getServletPath());
                    return true;
                }   
                final String requestHeader = request.getHeader(jwtProperties.getHeader());
                String sign = request.getHeader(HttpHeader.SIGN); //加密串，可自行规定是否使用
                String authToken = null;
                logger.info("输出-------------------"+requestHeader);
                if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
                    authToken = requestHeader.substring(7);
                    //验证token是否过期,包含了验证jwt是否正确
                    try {
                        boolean flag = jwtTokenUtil.isTokenExpired(authToken);
                        if (flag) {
                            RenderUtil.renderJson(response, new JsonResult(Message.M3007));
                            logger.info("被拦截-----------1111-------------"+request.getServletPath());
                            return false;
                        }
                        String loginName = jwtTokenUtil.getUsernameFromToken(authToken);//获取用户loginName
                        SgUser user = jwtTokenUtil.getUserFromToken(authToken);
                        Date date = jwtTokenUtil.getIssuedAtDateFromToken(authToken);//获取jwt发布时间
                        String md5key = jwtTokenUtil.getMd5KeyFromToken(authToken);//获取jwt发布时间
                        //解析token中的其他头参数
                        String ip = jwtTokenUtil.getPrivateClaimFromToken(authToken, HttpHeader.IP);
                        //TODO 在此获取头参数
                        
                        //封装登陆用户
                        if(user !=null){
                            header.setUserId(user.getId()); 
                        }
                        header.setToken(authToken);
                        header.setLoginName(loginName);
                        header.setMd5key(md5key);
                        header.setSign(sign);
                        HttpHeader.set(header);
                       
                        logger.info("请求IP:"+ ip ); 
                        logger.info("请求由"+loginName+"在【"+DateUtil.formatStandardDatetime(date)+"】发起请求");        
                        
                    } catch (JwtException e) {
                        e.printStackTrace();
                        //有异常就是token解析失败
                        RenderUtil.renderJson(response,new JsonResult(Message.M3008));
                        logger.info("被拦截--------------2222----------"+request.getServletPath());
                        return false;
                    }
                    logger.info("未被拦截-----------2222》》》》》》》》》》"+request.getServletPath());
                    return true;
                } else {
                    //无需鉴权
                    if(JwtProperties.getNoAuth().contains(request.getServletPath())){
                        logger.info("未被拦截-----------333333》》》》》》》》》》"+request.getServletPath());
                        return true;
                    }else{
                       logger.info("-------------------被拦截"+request.getServletPath());
                    }
                    //header没有带Bearer字段
                    RenderUtil.renderJson(response,new JsonResult(Message.M3010));
                    logger.info("被拦截------------33333------------"+request.getServletPath());
                    return false;
                }
            }
    }

    /**
     * 当前请求进行处理之后
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    /**
     * 整个请求结束之后
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        
    }
}

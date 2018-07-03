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
            logger.info("开始执行Token拦截器------------------------");
            if (request.getServletPath().equals("/" + jwtProperties.getAuthPath())) {
                // TODO 可在此处验证头参数
                return true;
            }
            final String requestHeader = request.getHeader(jwtProperties.getHeader());
            String authToken = null;
            if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
                authToken = requestHeader.substring(7);
                //验证token是否过期,包含了验证jwt是否正确
                try {
                    boolean flag = jwtTokenUtil.isTokenExpired(authToken);
                    if (flag) {
                        RenderUtil.renderJson(response, new JsonResult(Message.M5017));
                        return false;
                    }
                    String loginName = jwtTokenUtil.getUsernameFromToken(authToken);//获取用户id
                    Date date = jwtTokenUtil.getIssuedAtDateFromToken(authToken);//获取jwt发布时间
                    //解析token中的其他头参数
                    String ip = jwtTokenUtil.getPrivateClaimFromToken(authToken, HttpHeader.IP);
                    //TODO 在此获取头参数
                    
                    //封装登陆用户
                    HttpHeader header = new HttpHeader();
                    header.setUserId(loginName);
                    header.set(header);
                   
                    logger.info("请求IP:"+ ip ); 
                    logger.info("请求由"+loginName+"在【"+DateUtil.formatStandardDatetime(date)+"】发起请求");        
                    
                } catch (JwtException e) {
                    //有异常就是token解析失败
                    RenderUtil.renderJson(response,new JsonResult(Message.M3008));
                    return false;
                }
            } else {
                //header没有带Bearer字段
                RenderUtil.renderJson(response,new JsonResult(Message.M3010));
                return false;
            }
            return true;
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

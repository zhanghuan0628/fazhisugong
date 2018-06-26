package com.ffxl.hi.interceptor;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ffxl.cloud.model.FBeanRecord;
import com.ffxl.cloud.model.FBeanRule;
import com.ffxl.cloud.service.FBeanRecordService;
import com.ffxl.cloud.service.FBeanRuleService;
import com.ffxl.platform.util.HttpHeader;
import com.ffxl.platform.util.UUIDUtil;

public class PostHeaderInterceptor extends HandlerInterceptorAdapter{
    private static final Logger logger = LoggerFactory.getLogger(PostHeaderInterceptor.class);
    @Autowired
    private FBeanRecordService FBeanRecordService;
    @Autowired
    private FBeanRuleService fBeanRuleService;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("=======post请求头信息保存======");
        HttpHeader header = new HttpHeader();
        header.setAcceptLanguage(request.getHeader(HttpHeader.ACCEPT_LANGUAGE));
        header.setDeviceModel(request.getHeader(HttpHeader.DEVICE_MODEL));
        header.setDeviceBrand(request.getHeader(HttpHeader.DEVICE_BRAND));
        header.setSystemType(request.getHeader(HttpHeader.SYSTEM_TYPE));
        header.setSystemVersion(request.getHeader(HttpHeader.SYSTEM_VERSION));
        header.setAppType(request.getHeader(HttpHeader.APP_TYPE));
        header.setAppVersion(request.getHeader(HttpHeader.APP_VERSION));
        header.setToken(request.getHeader(HttpHeader.TOKEN));
        
        header.setAppId(request.getHeader(HttpHeader.APP_ID));
        header.setNonceStr(request.getHeader(HttpHeader.NONCE_STR));
        header.setTimestamp(request.getHeader(HttpHeader.TIMESTAMP));
        header.setSign(request.getHeader(HttpHeader.SIGN));

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        logger.info("获取ip地址:" + ip);
        header.setIp(ip);
        HttpHeader.set(header);

        logger.info("$$$$$$$$ header.toString()=" + header.toString());
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
        response.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}

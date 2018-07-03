package com.ffxl.api.auth.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ffxl.api.auth.util.JwtTokenUtil;
import com.ffxl.api.auth.validator.IReqValidator;
import com.ffxl.api.auth.validator.dto.AuthRequest;
import com.ffxl.api.auth.validator.dto.AuthResponse;
import com.ffxl.platform.constant.JsonResult;
import com.ffxl.platform.constant.Message;
import com.ffxl.platform.util.HttpHeader;

/**
 * 请求验证的
 * 
 * @author jiawei
 * 2018年7月2日
 */
@RestController
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Resource(name = "simpleValidator")
    private IReqValidator reqValidator;

    /**
     * 封装头请求信息到token中
     * jaiwei
     * 2018年7月3日下午5:29:39
     * @param authRequest
     * @return
     */
    @RequestMapping(value = "auth")
    public JsonResult createAuthenticationToken(AuthRequest authRequest,HttpServletRequest request, HttpServletResponse response) {
        logger.info("=======post请求头信息保存======");
        HttpHeader local= HttpHeader.get();
        local.setAcceptLanguage(request.getHeader(HttpHeader.ACCEPT_LANGUAGE));
        local.setDeviceModel(request.getHeader(HttpHeader.DEVICE_MODEL));
        local.setDeviceBrand(request.getHeader(HttpHeader.DEVICE_BRAND));
        local.setSystemType(request.getHeader(HttpHeader.SYSTEM_TYPE));
        local.setSystemVersion(request.getHeader(HttpHeader.SYSTEM_VERSION));
        local.setDeviceToken(request.getHeader(HttpHeader.DEVICE_TOKEN));
        local.setAppType(request.getHeader(HttpHeader.APP_TYPE));
        local.setAppVersion(request.getHeader(HttpHeader.APP_VERSION));
        boolean validate = reqValidator.validate(authRequest);
        
        if (validate) {
            final String randomKey = jwtTokenUtil.getRandomKey();
            final String token = jwtTokenUtil.generateToken(authRequest.getUserName(), randomKey);
            return new JsonResult(Message.M2000,new AuthResponse(token, randomKey));
        } else {
            return new JsonResult(Message.M3002);
        }
    }
}

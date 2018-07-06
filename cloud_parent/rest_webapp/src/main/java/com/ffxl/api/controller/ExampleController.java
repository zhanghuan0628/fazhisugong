package com.ffxl.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.ffxl.api.auth.converter.BaseTransferEntity;
import com.ffxl.api.auth.security.util.SignUtil;
import com.ffxl.cloud.model.SysUser;
import com.ffxl.platform.core.exception.BusinessException;
import com.ffxl.platform.util.HttpHeader;

/**
 * 常规控制器
 * 
 * @author jiawei
 * 2018年7月6日
 */
@Controller
@RequestMapping("/hello")
public class ExampleController {

    @RequestMapping("")
    public ResponseEntity hello() {
        HttpHeader local= HttpHeader.get();
        String userId = local.getUserId();
        String sign = local.getSign();
        SysUser simpleObject = new SysUser();
        simpleObject.setId(userId);
        BaseTransferEntity btf = SignUtil.createSign(simpleObject);
        if(!btf.getSign().equals(sign)){
            throw new BusinessException("数据被篡改");
        }
        System.out.println(JSON.toJSONString(btf));
        
        return ResponseEntity.ok("请求成功!登陆用户是："+userId);
    }
}

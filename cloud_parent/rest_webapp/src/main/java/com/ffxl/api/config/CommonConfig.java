package com.ffxl.api.config;

import org.springframework.beans.factory.annotation.Value;

public class CommonConfig {
    //通用登录密码
    @Value("#{settings['common-password']}")  
    public String COMMON_PASSWARD;  
}

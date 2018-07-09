package com.ffxl.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseController {
    @Value("#{settings['common-password']}")  
    public String COMMON_PASSWARD;  
    
}

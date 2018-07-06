package com.ffxl.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.ffxl.api.auth.converter.WithSignMessageConverter;

/**
 * 签名校验messageConverter
 * 
 * @author jiawei
 * 2018年7月3日
 */
@Component
public class MessageConverConfig {
    @Value("#{settings['sign-open']}")  
    public String SIGN_OPEN;  

    @Bean
    public WithSignMessageConverter withSignMessageConverter() {
        WithSignMessageConverter withSignMessageConverter = new WithSignMessageConverter();
        DefaultFastjsonConfig defaultFastjsonConfig = new DefaultFastjsonConfig();
        withSignMessageConverter.setFastJsonConfig(defaultFastjsonConfig.fastjsonConfig());
        withSignMessageConverter.setSupportedMediaTypes(defaultFastjsonConfig.getSupportedMediaType());
        return withSignMessageConverter;
    }
}

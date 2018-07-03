package com.ffxl.api.auth.validator.dto;

/**
 * 验证的凭据
 * 
 * @author jiawei
 * 2018年7月2日
 */
public interface Credence {

    /**
     * 凭据名称
     */
    String getCredenceName();

    /**
     * 密码或者是其他的验证码之类的
     */
    String getCredenceCode();
}

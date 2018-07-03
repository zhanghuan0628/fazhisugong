package com.ffxl.api.auth.validator.impl;

import org.springframework.stereotype.Service;

import com.ffxl.api.auth.validator.IReqValidator;
import com.ffxl.api.auth.validator.dto.Credence;

/**
 * 直接验证账号密码是不是admin
 * 
 * @author jiawei
 * 2018年7月3日
 */
@Service
public class SimpleValidator implements IReqValidator {

    private static String USER_NAME = "admin";

    private static String PASSWORD = "admin";

    @Override
    public boolean validate(Credence credence) {

        String userName = credence.getCredenceName();
        String password = credence.getCredenceCode();

        if (USER_NAME.equals(userName) && PASSWORD.equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}

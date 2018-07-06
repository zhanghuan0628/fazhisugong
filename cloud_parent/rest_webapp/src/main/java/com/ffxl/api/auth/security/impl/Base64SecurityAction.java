package com.ffxl.api.auth.security.impl;

import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import com.ffxl.api.auth.security.DataSecurityAction;

/**
 * encodeToString
 * 
 * @author jiawei
 * 2018年7月3日
 */
@Component
public class Base64SecurityAction implements DataSecurityAction {

    @Override
    public String doAction(String beProtected) {
        return Base64Utils.encodeToString(beProtected.getBytes());
    }

    @Override
    public String unlock(String securityCode) {
        byte[] bytes = Base64Utils.decodeFromString(securityCode);
        return new String(bytes);
    }
}

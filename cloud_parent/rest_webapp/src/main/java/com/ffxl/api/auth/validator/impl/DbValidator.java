package com.ffxl.api.auth.validator.impl;

import java.util.List;

import javax.xml.registry.infomodel.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffxl.api.auth.validator.IReqValidator;
import com.ffxl.api.auth.validator.dto.Credence;
import com.ffxl.cloud.model.SgUser;
import com.ffxl.cloud.service.SgUserService;
import com.ffxl.platform.util.MD5Util;

/**
 * 账号密码验证
 * 
 * @author jiawei
 * 2018年7月3日
 */
@Service
public class DbValidator implements IReqValidator {

	@Autowired
	SgUserService sgUserService;

    @Override
    public boolean validate(Credence credence) {
        SgUser user = new SgUser();
        user.setLoginName(credence.getCredenceName());
        SgUser quser = sgUserService.queryByModel(user);
        if (quser != null) {
            String md5Code = MD5Util.encrypt(credence.getCredenceCode());
            if(quser.getPassword().equals(md5Code)){
                return true;
            }else{
                return false;
            }
        } else {
            return false;
        }
    }
}

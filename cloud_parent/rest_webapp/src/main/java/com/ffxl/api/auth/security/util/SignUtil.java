package com.ffxl.api.auth.security.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.ffxl.api.auth.converter.BaseTransferEntity;
import com.ffxl.api.auth.security.impl.Base64SecurityAction;
import com.ffxl.cloud.model.SysUser;
import com.ffxl.platform.core.support.HttpKit;
import com.ffxl.platform.util.HttpHeader;
import com.ffxl.platform.util.MD5Util;

public class SignUtil {
    private static final Logger logger = LoggerFactory.getLogger(SignUtil.class);
    
    public static BaseTransferEntity createSign(Object obj){
        String jsonString = JSON.toJSONString(obj);
        String encode = new Base64SecurityAction().doAction(jsonString); 
        //获取随机数
        HttpHeader local= HttpHeader.get();
        String md5key = local.getMd5key();
        String md5 = MD5Util.encrypt(encode + md5key);

        BaseTransferEntity baseTransferEntity = new BaseTransferEntity();
        baseTransferEntity.setObject(encode);
        baseTransferEntity.setSign(md5);
        logger.info("加密数据："+JSON.toJSONString(baseTransferEntity));
        return baseTransferEntity;
        
    }
}

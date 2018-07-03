package com.ffxl.api.auth.converter;

/**
 * 基础的传输bean
 * 
 * @author jiawei
 * 2018年7月3日
 */
public class BaseTransferEntity {

    private String object; //base64编码的json字符串

    private String sign;   //签名

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}

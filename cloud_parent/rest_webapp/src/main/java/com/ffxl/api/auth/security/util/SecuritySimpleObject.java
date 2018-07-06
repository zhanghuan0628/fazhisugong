package com.ffxl.api.auth.security.util;

/**
 * 签名校验对象
 * @author jiawei
 * 2018年7月4日
 */
public class SecuritySimpleObject {
    private String userId;

    private String methodName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}

package com.ffxl.platform.constant.status;

/**
 * 管理员的状态
 *
 * @author fengshuonan
 * @Date 2017年1月10日 下午9:54:13
 */
public enum SysUserStatus {

    OK(1, "启用"), FREEZED(2, "禁用");

    int code;
    String message;

    SysUserStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String valueOf(Integer value) {
        if (value == null) {
            return "";
        } else {
            for (SysUserStatus ms : SysUserStatus.values()) {
                if (ms.getCode() == value) {
                    return ms.getMessage();
                }
            }
            return "";
        }
    }
}

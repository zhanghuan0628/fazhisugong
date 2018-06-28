package com.ffxl.admin.core.log.factory;

import java.util.Date;

import com.ffxl.cloud.model.SysLoginLog;
import com.ffxl.cloud.model.SysOperationLog;
import com.ffxl.platform.constant.Message;
import com.ffxl.platform.constant.status.BizLogType;
import com.ffxl.platform.constant.status.LogType;
import com.ffxl.platform.util.UUIDUtil;

/**
 * 日志对象创建工厂
 * 
 * @author jiawei
 * 2018年6月28日
 */
public class LogFactory {

    /**
     * 创建操作日志
     */
    public static SysOperationLog createOperationLog(BizLogType bizLogType, String loginName, String bussinessName, String clazzName, String methodName, String msg, String businessCode) {
        SysOperationLog operationLog = new SysOperationLog();
        operationLog.setId(UUIDUtil.getUUID());
        operationLog.setLogType(bizLogType.getMessage());
        operationLog.setLogName(bussinessName);
        operationLog.setLoginName(loginName);
        operationLog.setClassName(clazzName);
        operationLog.setMethodName(methodName);
        operationLog.setCreateTime(new Date());
        operationLog.setSucceed(Message.getMessage(businessCode));
        operationLog.setMessage(msg);
        return operationLog;
    }

    /**
     * 创建登录日志
     */
    public static SysLoginLog createLoginLog(LogType logType, String loginName, String msg, String ip) {
        SysLoginLog loginLog = new SysLoginLog();
        loginLog.setId(UUIDUtil.getUUID());
        loginLog.setLogName(logType.getMessage());
        loginLog.setLoginName(loginName);
        loginLog.setCreateTime(new Date());
        loginLog.setSucceed(Message.getMessage(Message.M2000));
        loginLog.setIp(ip);
        loginLog.setMessage(msg);
        return loginLog;
    }
}

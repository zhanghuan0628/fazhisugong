package com.ffxl.admin.core.log.factory;

import java.util.TimerTask;
import java.util.logging.LogManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ffxl.admin.core.util.ApplicationContextUtils;
import com.ffxl.cloud.model.SysLoginLog;
import com.ffxl.cloud.model.SysOperationLog;
import com.ffxl.cloud.service.SysLoginLogService;
import com.ffxl.cloud.service.SysOperationLogService;
import com.ffxl.platform.constant.Message;
import com.ffxl.platform.constant.status.BizLogType;
import com.ffxl.platform.constant.status.LogType;
import com.ffxl.platform.util.ToolUtil;


/**
 * 日志操作任务创建工厂
 * @author jiawei
 * 2018年6月28日
 */
@Component
public class LogTaskFactory {

    private static Logger logger = LoggerFactory.getLogger(LogManager.class);
    
    public static TimerTask loginLog(final String loginName, final String ip) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    SysLoginLog loginLog = LogFactory.createLoginLog(LogType.LOGIN, loginName, null, ip);
                    SysLoginLogService sysLoginLogService = ApplicationContextUtils.getBean(SysLoginLogService.class); 
                    sysLoginLogService.insert(loginLog);
                } catch (Exception e) {
                    logger.error("创建登录日志异常!", e);
                }
            }
        };
    }

    public static TimerTask loginLog(final String loginName, final String msg, final String ip) {
        return new TimerTask() {
            @Override
            public void run() {
                SysLoginLog loginLog = LogFactory.createLoginLog(
                        LogType.LOGIN_FAIL, null, "登录账号:" + loginName + "," + msg, ip);
                try {
                    SysLoginLogService sysLoginLogService = ApplicationContextUtils.getBean(SysLoginLogService.class); 
                    sysLoginLogService.insert(loginLog);
                } catch (Exception e) {
                    logger.error("创建登录失败异常!", e);
                }
            }
        };
    }

    public static TimerTask exitLog(final String loginName, final String ip) {
        return new TimerTask() {
            @Override
            public void run() {
                SysLoginLog loginLog = LogFactory.createLoginLog(LogType.EXIT, loginName, null,ip);
                try {
                    SysLoginLogService sysLoginLogService = ApplicationContextUtils.getBean(SysLoginLogService.class); 
                    sysLoginLogService.insert(loginLog);
                } catch (Exception e) {
                    logger.error("创建退出日志异常!", e);
                }
            }
        };
    }

    public static TimerTask bussinessLog(final String loginName, final String bussinessName, final String clazzName, final String methodName, final String msg) {
        return new TimerTask() {
            @Override
            public void run() {
                SysOperationLog operationLog = LogFactory.createOperationLog(
                        BizLogType.BUSSINESS, loginName, bussinessName, clazzName, methodName, msg, Message.M2000);
                try {
                    SysOperationLogService sysOperationLogService =  ApplicationContextUtils.getBean(SysOperationLogService.class);
                    sysOperationLogService.insert(operationLog);
                } catch (Exception e) {
                    logger.error("创建业务日志异常!", e);
                }
            }
        };
    }

    public static TimerTask exceptionLog(final String loginName, final Exception exception) {
        return new TimerTask() {
            @Override
            public void run() {
                String msg = ToolUtil.getExceptionMsg(exception);
                SysOperationLog operationLog = LogFactory.createOperationLog(
                        BizLogType.EXCEPTION, loginName, "", null, null, msg, Message.M5000);
                try {
                    SysOperationLogService sysOperationLogService =  ApplicationContextUtils.getBean(SysOperationLogService.class);
                    sysOperationLogService.insert(operationLog);
                } catch (Exception e) {
                    logger.error("创建异常日志异常!", e);
                }
            }
        };
    }
}

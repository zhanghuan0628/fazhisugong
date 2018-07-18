package com.ffxl.cloud.service;

import java.util.List;

import com.ffxl.cloud.model.SysLoginLog;
import com.ffxl.cloud.model.SysLoginLogExample;
import com.ffxl.platform.core.GenericService;
import com.ffxl.platform.core.Page;

 /**
 * Generate By MBG 
 **/
public interface SysLoginLogService extends GenericService<SysLoginLog, SysLoginLogExample, String> {
     /**
     * According to the model information query object  
     * @param BaseSysLoginLog
     * @return 
     **/
    SysLoginLog queryByModel(SysLoginLog sysLoginLog);
    /**
     * 登录日志
     * @param sysLoginLog
     * @param page
     * @return
     */
	List<SysLoginLog> queryPageList(SysLoginLog sysLoginLog, Page page);
}
package com.ffxl.cloud.service;

import com.ffxl.cloud.model.SysLoginLog;
import com.ffxl.cloud.model.SysLoginLogExample;
import com.ffxl.platform.core.GenericService;

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
}
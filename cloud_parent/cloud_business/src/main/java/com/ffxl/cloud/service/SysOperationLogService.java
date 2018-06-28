package com.ffxl.cloud.service;

import com.ffxl.cloud.model.SysOperationLog;
import com.ffxl.cloud.model.SysOperationLogExample;
import com.ffxl.platform.core.GenericService;

 /**
 * Generate By MBG 
 **/
public interface SysOperationLogService extends GenericService<SysOperationLog, SysOperationLogExample, String> {
     /**
     * According to the model information query object  
     * @param BaseSysOperationLog
     * @return 
     **/
    SysOperationLog queryByModel(SysOperationLog sysOperationLog);
}
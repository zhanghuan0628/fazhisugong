package com.ffxl.cloud.service;

import java.util.List;

import com.ffxl.cloud.model.SysOperationLog;
import com.ffxl.cloud.model.SysOperationLogExample;
import com.ffxl.platform.core.GenericService;
import com.ffxl.platform.core.Page;

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
    /**
     * 业务日志
     * @param sysOperationLog
     * @param page
     * @return
     */
	List<SysOperationLog> queryPageList(SysOperationLog sysOperationLog, Page page);
}
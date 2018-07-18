package com.ffxl.cloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffxl.cloud.model.SysOperationLog;
import com.ffxl.cloud.model.SysOperationLogExample;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.Page;

 /**
 * Generate By MBG 
 **/
public interface SysOperationLogMapper extends GenericMapper<SysOperationLog, SysOperationLogExample, String> {
	/**
	 * 业务日志
	 * @param sysOperationLog
	 * @param page
	 * @return
	 */
	List<SysOperationLog> queryPageList(@Param("model")SysOperationLog sysOperationLog, @Param("page")Page page);
}
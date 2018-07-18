package com.ffxl.cloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffxl.cloud.model.SysLoginLog;
import com.ffxl.cloud.model.SysLoginLogExample;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.Page;

 /**
 * Generate By MBG 
 **/
public interface SysLoginLogMapper extends GenericMapper<SysLoginLog, SysLoginLogExample, String> {
	/**
	 * 登录日志
	 * @param sysLoginLog
	 * @param page
	 * @return
	 */
	List<SysLoginLog> queryPageList(@Param("model")SysLoginLog sysLoginLog, @Param("page")Page page);
}
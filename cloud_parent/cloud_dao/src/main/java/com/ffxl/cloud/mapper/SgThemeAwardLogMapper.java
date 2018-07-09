package com.ffxl.cloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffxl.cloud.model.SgThemeAwardLog;
import com.ffxl.cloud.model.SgThemeAwardLogExample;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.Page;

 /**
 * Generate By MBG 
 **/
public interface SgThemeAwardLogMapper extends GenericMapper<SgThemeAwardLog, SgThemeAwardLogExample, String> {
	/**
	 * 查询用户答题列表
	 * @param model
	 * @param page
	 * @return
	 */
	List<SgThemeAwardLog> queryThemePageList(@Param("model")SgThemeAwardLog model, @Param("page")Page page);
}
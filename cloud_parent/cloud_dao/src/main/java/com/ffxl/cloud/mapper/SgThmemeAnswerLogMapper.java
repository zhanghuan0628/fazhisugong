package com.ffxl.cloud.mapper;

import org.apache.ibatis.annotations.Param;

import com.ffxl.cloud.model.SgThmemeAnswerLog;
import com.ffxl.cloud.model.SgThmemeAnswerLogExample;
import com.ffxl.platform.core.GenericMapper;

 /**
 * Generate By MBG 
 **/
public interface SgThmemeAnswerLogMapper extends GenericMapper<SgThmemeAnswerLog, SgThmemeAnswerLogExample, String> {
	/**
	 * 用户成绩
	 * @param id
	 * @return
	 */
	SgThmemeAnswerLog queryThemeAnswerUser(@Param("id")String id);
}
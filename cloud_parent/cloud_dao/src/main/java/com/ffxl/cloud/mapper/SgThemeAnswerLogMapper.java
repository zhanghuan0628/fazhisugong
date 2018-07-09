package com.ffxl.cloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffxl.cloud.model.SgThemeAnswerLog;
import com.ffxl.cloud.model.SgThemeAnswerLogExample;
import com.ffxl.platform.core.GenericMapper;

 /**
 * Generate By MBG 
 **/
public interface SgThemeAnswerLogMapper extends GenericMapper<SgThemeAnswerLog, SgThemeAnswerLogExample, String> {
	/**
	 * 用户回答详情
	 * @param userId
	 * @param themeId
	 * @return
	 */
	SgThemeAnswerLog queryAnswerLogByUser(@Param("userId")String userId, @Param("themeId")String themeId);
	/**
	 * 我是法官
	 * @param userId
	 * @return
	 */
	List<SgThemeAnswerLog> queryMyTheme(@Param("userId")String userId);
	/**
	 * 查询最大时间
	 * @param userId
	 * @return
	 */
	SgThemeAnswerLog queryMaxDateById(@Param("userId")String userId);
}
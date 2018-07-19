package com.ffxl.cloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffxl.cloud.model.SgThemeAnswerLog;
import com.ffxl.cloud.model.SgThemeAnswerLogExample;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.Page;

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
	List<SgThemeAnswerLog> queryMyTheme(@Param("userId")String userId,@Param("page")Page page);
	/**
	 * 查询最大时间
	 * @param userId
	 * @return
	 */
	SgThemeAnswerLog queryMaxDateById(@Param("userId")String userId);
	/**
	 * 
	 * @param model
	 * @param page
	 * @return
	 */
	List<SgThemeAnswerLog> queryThemeList(@Param("model")SgThemeAnswerLog model, @Param("page")Page page);
}
package com.ffxl.cloud.service;

import java.util.List;

import com.ffxl.cloud.model.SgSubject;
import com.ffxl.cloud.model.SgThemeAnswerLog;
import com.ffxl.cloud.model.SgThemeAnswerLogExample;
import com.ffxl.platform.core.GenericService;
import com.ffxl.platform.core.Page;

 /**
 * Generate By MBG 
 **/
public interface SgThemeAnswerLogService extends GenericService<SgThemeAnswerLog, SgThemeAnswerLogExample, String> {
     /**
     * According to the model information query object  
     * @param BaseSgThemeAnswerLog
     * @return 
     **/
    SgThemeAnswerLog queryByModel(SgThemeAnswerLog sgThemeAnswerLog);
    /**
     * 用户回答详情
     * @param userId
     * @param themeId
     * @return
     */
	SgThemeAnswerLog queryAnswerLogByUser(String userId, String themeId);
	/**
	 * 我是法官
	 * @param userId
	 * @return
	 */
	List<SgThemeAnswerLog> queryMyTheme(String userId,Page page);
	/**
	 * 查询最大时间
	 * @param userId
	 * @return
	 */
	SgThemeAnswerLog queryMaxDateById(String userId);
	/**
	 * 回顾考题
	 * @param userId
	 * @param themeId
	 * @return
	 */
	List<SgSubject> queryUserBackTheme(String userId, String themeId);
}
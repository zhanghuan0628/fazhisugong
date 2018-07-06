package com.ffxl.cloud.service;

import java.util.List;

import com.ffxl.cloud.model.SgThemeAnswerLog;
import com.ffxl.cloud.model.SgThemeAnswerLogExample;
import com.ffxl.platform.core.GenericService;

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
	List<SgThemeAnswerLog> queryMyTheme(String userId);
}
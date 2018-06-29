package com.ffxl.cloud.service;

import com.ffxl.cloud.model.SgThmemeAnswerLog;
import com.ffxl.cloud.model.SgThmemeAnswerLogExample;
import com.ffxl.platform.core.GenericService;

 /**
 * Generate By MBG 
 **/
public interface SgThmemeAnswerLogService extends GenericService<SgThmemeAnswerLog, SgThmemeAnswerLogExample, String> {
     /**
     * According to the model information query object  
     * @param BaseSgThmemeAnswerLog
     * @return 
     **/
    SgThmemeAnswerLog queryByModel(SgThmemeAnswerLog sgThmemeAnswerLog);
    /**
     * 用户成绩
     * @param id
     * @return
     */
	SgThmemeAnswerLog queryThemeAnswerUser(String id);
}
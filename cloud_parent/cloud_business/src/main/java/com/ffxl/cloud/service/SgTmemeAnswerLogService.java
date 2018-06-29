package com.ffxl.cloud.service;

import com.ffxl.cloud.model.SgTmemeAnswerLog;
import com.ffxl.cloud.model.SgTmemeAnswerLogExample;
import com.ffxl.platform.core.GenericService;

 /**
 * Generate By MBG 
 **/
public interface SgTmemeAnswerLogService extends GenericService<SgTmemeAnswerLog, SgTmemeAnswerLogExample, String> {
     /**
     * According to the model information query object  
     * @param BaseSgTmemeAnswerLog
     * @return 
     **/
    SgTmemeAnswerLog queryByModel(SgTmemeAnswerLog sgTmemeAnswerLog);
}
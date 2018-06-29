package com.ffxl.cloud.service;

import com.ffxl.cloud.model.SgThemeAwardLog;
import com.ffxl.cloud.model.SgThemeAwardLogExample;
import com.ffxl.platform.core.GenericService;

 /**
 * Generate By MBG 
 **/
public interface SgThemeAwardLogService extends GenericService<SgThemeAwardLog, SgThemeAwardLogExample, String> {
     /**
     * According to the model information query object  
     * @param BaseSgThemeAwardLog
     * @return 
     **/
    SgThemeAwardLog queryByModel(SgThemeAwardLog sgThemeAwardLog);
}
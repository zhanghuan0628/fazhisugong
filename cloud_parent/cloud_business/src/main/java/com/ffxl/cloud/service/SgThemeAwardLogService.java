package com.ffxl.cloud.service;

import java.util.List;

import com.ffxl.cloud.model.SgThemeAwardLog;
import com.ffxl.cloud.model.SgThemeAwardLogExample;
import com.ffxl.platform.core.GenericService;
import com.ffxl.platform.core.Page;

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
    /**
     * 查询用户答题列表
     * @param model
     * @param page
     * @return
     */
	List<SgThemeAwardLog> queryThemePageList(SgThemeAwardLog model, Page page);
}
package com.ffxl.cloud.service;

import java.util.List;

import com.ffxl.cloud.model.SgTheme;
import com.ffxl.cloud.model.SgThemeExample;
import com.ffxl.platform.core.GenericService;
import com.ffxl.platform.core.Page;

 /**
 * Generate By MBG 
 **/
public interface SgThemeService extends GenericService<SgTheme, SgThemeExample, String> {
     /**
     * According to the model information query object  
     * @param BaseSgTheme
     * @return 
     **/
    SgTheme queryByModel(SgTheme sgTheme);
    /**
     * 查询答题人数管理列表
     * @param sgTheme
     * @param page
     * @return
     */
	List<SgTheme> queryPageList(SgTheme sgTheme, Page page);
}
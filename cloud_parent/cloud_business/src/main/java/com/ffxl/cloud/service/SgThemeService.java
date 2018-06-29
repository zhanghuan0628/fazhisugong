package com.ffxl.cloud.service;

import com.ffxl.cloud.model.SgTheme;
import com.ffxl.cloud.model.SgThemeExample;
import com.ffxl.platform.core.GenericService;

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
}
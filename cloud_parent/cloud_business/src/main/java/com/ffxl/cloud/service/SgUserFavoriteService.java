package com.ffxl.cloud.service;

import com.ffxl.cloud.model.SgUserFavorite;
import com.ffxl.cloud.model.SgUserFavoriteExample;
import com.ffxl.platform.core.GenericService;

 /**
 * Generate By MBG 
 **/
public interface SgUserFavoriteService extends GenericService<SgUserFavorite, SgUserFavoriteExample, String> {
     /**
     * According to the model information query object  
     * @param BaseSgUserFavorite
     * @return 
     **/
    SgUserFavorite queryByModel(SgUserFavorite sgUserFavorite);
}
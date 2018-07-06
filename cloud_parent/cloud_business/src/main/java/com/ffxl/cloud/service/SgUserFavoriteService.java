package com.ffxl.cloud.service;

import java.util.List;

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
    /**
     * 我的收藏
     * @param userId
     * @return
     */
	List<SgUserFavorite> queryMyFavorite(String userId, String sourceType);
}
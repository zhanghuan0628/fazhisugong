package com.ffxl.cloud.service;

import java.util.List;

import com.ffxl.cloud.model.SgUser;
import com.ffxl.cloud.model.SgUserExample;
import com.ffxl.platform.core.GenericService;
import com.ffxl.platform.core.Page;

 /**
 * Generate By MBG 
 **/
public interface SgUserService extends GenericService<SgUser, SgUserExample, String> {
     /**
     * According to the model information query object  
     * @param BaseSgUser
     * @return 
     **/
    SgUser queryByModel(SgUser sgUser);
    /**
     * 用户列表
     * @param sgUser
     * @param page
     * @return
     */
	List<SgUser> queryPageList(SgUser sgUser, Page page);
}
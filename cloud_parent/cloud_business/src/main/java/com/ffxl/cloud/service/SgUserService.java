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
	/**
	 * 我的消息的数量
	 * @param userId
	 * @return
	 */
	int queryMyInfo(String userId);
	/**
	 * 我的消息的列表
	 * @param userId
	 * @return
	 */
	List<SgUser> queryMyInfoList(String userId);
	/**
	 * token清空
	 * @param userId
	 * @return
	 */
	int updateTokenNull(String userId);
}
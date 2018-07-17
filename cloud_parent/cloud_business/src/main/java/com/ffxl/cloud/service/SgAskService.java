package com.ffxl.cloud.service;

import java.util.List;

import com.ffxl.cloud.model.SgAsk;
import com.ffxl.cloud.model.SgAskExample;
import com.ffxl.platform.core.GenericService;
import com.ffxl.platform.core.Page;

 /**
 * Generate By MBG 
 **/
public interface SgAskService extends GenericService<SgAsk, SgAskExample, String> {
     /**
     * According to the model information query object  
     * @param BaseSgAsk
     * @return 
     **/
    SgAsk queryByModel(SgAsk sgAsk);
    /**
     * 用户咨询
     * @param sgAsk
     * @param page
     * @return
     */
	List<SgAsk> queryPageList(SgAsk sgAsk, Page page);
	/**
	 * 用户咨询详情
	 * @param id
	 * @return
	 */
	SgAsk queryUserAsk(String id);
	/**
	 * 用户咨询详情
	 * @param topicId
	 * @return
	 */
	SgAsk queryUserAskById(String topicId);
	/**
	 * 我的咨询
	 * @param userId
	 * @return
	 */
	List<SgAsk> queryMyAsk(String userId,Page page);
}
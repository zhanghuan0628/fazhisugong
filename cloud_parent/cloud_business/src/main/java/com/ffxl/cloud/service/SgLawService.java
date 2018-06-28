package com.ffxl.cloud.service;

import java.util.List;

import com.ffxl.cloud.model.SgLaw;
import com.ffxl.cloud.model.SgLawExample;
import com.ffxl.platform.core.GenericService;
import com.ffxl.platform.core.Page;

 /**
 * Generate By MBG 
 **/
public interface SgLawService extends GenericService<SgLaw, SgLawExample, String> {
     /**
     * According to the model information query object  
     * @param BaseSgLaw
     * @return 
     **/
    SgLaw queryByModel(SgLaw sgLaw);
    /**
     * 查询苏供法宝
     * @param sgLaw
     * @param page
     * @return
     */
	List<SgLaw> queryPageList(SgLaw sgLaw, Page page);
	/**
	 * 上下移
	 * @param bb
	 * @return
	 */
	int updateSort(SgLaw bb);
	/**
	 * 查询最大号
	 * @param code
	 * @param category
	 * @return
	 */
	int selectMaxSort(String id,String code, String category);
}
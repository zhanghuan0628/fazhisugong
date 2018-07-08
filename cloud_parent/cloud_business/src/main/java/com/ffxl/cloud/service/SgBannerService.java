package com.ffxl.cloud.service;

import java.util.List;

import com.ffxl.cloud.model.SgBanner;
import com.ffxl.cloud.model.SgBannerExample;
import com.ffxl.platform.core.GenericService;
import com.ffxl.platform.core.Page;

 /**
 * Generate By MBG 
 **/
public interface SgBannerService extends GenericService<SgBanner, SgBannerExample, String> {
     /**
     * According to the model information query object  
     * @param BaseSgBanner
     * @return 
     **/
    SgBanner queryByModel(SgBanner sgBanner);
    /**
     * 查询banner列表
     * @param sgBanner
     * @param page
     * @return
     */
	List<SgBanner> queryPageList(SgBanner sgBanner, Page page);
	/**
	 * 查询最大num
	 * @return
	 */
	int queryMaxNum();
	/**
	 * 上下移
	 * @param sb
	 * @return
	 */
	int updateSort(SgBanner sb);
}
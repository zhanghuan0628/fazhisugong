package com.ffxl.cloud.service;

import java.util.List;
import java.util.Map;

import com.ffxl.cloud.model.SgSubject;
import com.ffxl.cloud.model.SgSubjectExample;
import com.ffxl.platform.core.GenericService;
import com.ffxl.platform.core.Page;

 /**
 * Generate By MBG 
 **/
public interface SgSubjectService extends GenericService<SgSubject, SgSubjectExample, String> {
     /**
     * According to the model information query object  
     * @param BaseSgSubject
     * @return 
     **/
    SgSubject queryByModel(SgSubject sgSubject);
    /**
     * 法官题目
     * @param sgSubject
     * @param page
     * @return
     */
	List<SgSubject> queryPageList(SgSubject sgSubject, Page page);
	/**
	 * 查询题库
	 * @return
	 */
	Map queryCheckTheme(String userId);
	/**
	 * 根据活动找到题库
	 * @param themeId
	 * @param num
	 * @return
	 */
	List<SgSubject> querySubjectByTheme(String themeId, int num);
}
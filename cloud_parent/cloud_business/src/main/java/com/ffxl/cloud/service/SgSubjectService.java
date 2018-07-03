package com.ffxl.cloud.service;

import java.util.List;

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
}
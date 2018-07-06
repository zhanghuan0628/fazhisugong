package com.ffxl.cloud.service;

import java.util.List;

import com.ffxl.cloud.model.SgLawComment;
import com.ffxl.cloud.model.SgLawCommentExample;
import com.ffxl.platform.core.GenericService;
import com.ffxl.platform.core.Page;

 /**
 * Generate By MBG 
 **/
public interface SgLawCommentService extends GenericService<SgLawComment, SgLawCommentExample, String> {
     /**
     * According to the model information query object  
     * @param BaseSgLawComment
     * @return 
     **/
    SgLawComment queryByModel(SgLawComment sgLawComment);
    /**
     * 风险评论
     * @param sgLawComment
     * @param page
     * @return
     */
	List<SgLawComment> queryPageList(SgLawComment sgLawComment, Page page);
	/**
	 * 留言详情
	 * @param string
	 */
	List<SgLawComment> queryLawCommentDetail(String string);
}
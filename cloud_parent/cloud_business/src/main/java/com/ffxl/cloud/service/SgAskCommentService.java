package com.ffxl.cloud.service;

import java.util.List;

import com.ffxl.cloud.model.SgAskComment;
import com.ffxl.cloud.model.SgAskCommentExample;
import com.ffxl.platform.core.GenericService;
import com.ffxl.platform.core.Page;

 /**
 * Generate By MBG 
 **/
public interface SgAskCommentService extends GenericService<SgAskComment, SgAskCommentExample, String> {
     /**
     * According to the model information query object  
     * @param BaseSgAskComment
     * @return 
     **/
    SgAskComment queryByModel(SgAskComment sgAskComment);
    /**
     * 查询所有咨询回答
     * @return
     */
	List<SgAskComment> queryAllAskComment(String id,Page page);
}
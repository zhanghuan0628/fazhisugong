package com.ffxl.cloud.service;

import com.ffxl.cloud.model.SgAskComment;
import com.ffxl.cloud.model.SgAskCommentExample;
import com.ffxl.platform.core.GenericService;

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
}
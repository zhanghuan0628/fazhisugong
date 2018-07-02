package com.ffxl.cloud.service;

import com.ffxl.cloud.model.SgLawComment;
import com.ffxl.cloud.model.SgLawCommentExample;
import com.ffxl.platform.core.GenericService;

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
}
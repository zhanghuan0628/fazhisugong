package com.ffxl.cloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffxl.cloud.model.SgLawComment;
import com.ffxl.cloud.model.SgLawCommentExample;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.Page;

 /**
 * Generate By MBG 
 **/
public interface SgLawCommentMapper extends GenericMapper<SgLawComment, SgLawCommentExample, String> {
	/**
	 * 风险评论
	 * @param sgLawComment
	 * @param page
	 * @return
	 */
	List<SgLawComment> queryPageList(@Param("model")SgLawComment sgLawComment, @Param("page")Page page);
}
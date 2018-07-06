package com.ffxl.cloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffxl.cloud.model.SgAskComment;
import com.ffxl.cloud.model.SgAskCommentExample;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.Page;

 /**
 * Generate By MBG 
 **/
public interface SgAskCommentMapper extends GenericMapper<SgAskComment, SgAskCommentExample, String> {
	/**
	 * 查询所有咨询回答
	 * @return
	 */
	List<SgAskComment> queryAllAskComment(@Param("id")String id,@Param("page")Page page);
}
package com.ffxl.cloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffxl.cloud.model.SgAsk;
import com.ffxl.cloud.model.SgAskExample;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.Page;

 /**
 * Generate By MBG 
 **/
public interface SgAskMapper extends GenericMapper<SgAsk, SgAskExample, String> {
	/**
	 * 用户咨询
	 * @param sgAsk
	 * @param page
	 * @return
	 */
	List<SgAsk> queryPageList(@Param("model")SgAsk sgAsk, @Param("page")Page page);
	/**
	 * 用户咨询详情
	 * @param id
	 * @return
	 */
	SgAsk queryUserAsk(@Param("id")String id);
	/**
	 * 用户咨询详情
	 * @param topicId
	 * @return
	 */
	SgAsk queryUserAskById(@Param("topicId")String topicId);
}
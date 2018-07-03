package com.ffxl.cloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffxl.cloud.model.SgSubject;
import com.ffxl.cloud.model.SgSubjectExample;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.Page;

 /**
 * Generate By MBG 
 **/
public interface SgSubjectMapper extends GenericMapper<SgSubject, SgSubjectExample, String> {
	/**
	 * 法官题目
	 * @param sgSubject
	 * @param page
	 * @return
	 */
	List<SgSubject> queryPageList(@Param("model")SgSubject sgSubject, @Param("page")Page page);
}
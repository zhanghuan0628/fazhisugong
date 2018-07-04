package com.ffxl.cloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffxl.cloud.model.SgTheme;
import com.ffxl.cloud.model.SgThemeExample;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.Page;

 /**
 * Generate By MBG 
 **/
public interface SgThemeMapper extends GenericMapper<SgTheme, SgThemeExample, String> {
	/**
	 * 查询答题人数管理列表
	 * @param sgTheme
	 * @param page
	 * @return
	 */
	List<SgTheme> queryPageList(@Param("model")SgTheme sgTheme, @Param("page")Page page);
	/**
	 * 查询最大期数
	 * @return
	 */
	int selectMaxNum();
	/**
	 * 最大结束日期
	 * @return
	 */
	SgTheme selectMaxEndDate(@Param("num")String num);
}
package com.ffxl.cloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffxl.cloud.model.SgBanner;
import com.ffxl.cloud.model.SgBannerExample;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.Page;

 /**
 * Generate By MBG 
 **/
public interface SgBannerMapper extends GenericMapper<SgBanner, SgBannerExample, String> {
	/**
	 * 查询banner列表
	 * @param sgBanner
	 * @param page
	 * @return
	 */
	List<SgBanner> queryPageList(@Param("model")SgBanner sgBanner, @Param("page")Page page);
	/**
	 * 查询最大num
	 * @return
	 */
	int queryMaxNum();
	/**
	 * 上下移
	 * @param sb
	 * @return
	 */
	int updateSort(@Param("model")SgBanner sb);
}
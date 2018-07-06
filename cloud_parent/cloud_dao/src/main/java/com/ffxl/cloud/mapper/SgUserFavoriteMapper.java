package com.ffxl.cloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffxl.cloud.model.SgUserFavorite;
import com.ffxl.cloud.model.SgUserFavoriteExample;
import com.ffxl.platform.core.GenericMapper;

 /**
 * Generate By MBG 
 **/
public interface SgUserFavoriteMapper extends GenericMapper<SgUserFavorite, SgUserFavoriteExample, String> {
	/**
	 * 我的收藏
	 * @param userId
	 * @param sourceType
	 * @return
	 */
	List<SgUserFavorite> queryMyFavorite(@Param("userId")String userId, @Param("sourceType")String sourceType);
}
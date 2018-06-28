package com.ffxl.cloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffxl.cloud.model.SgUser;
import com.ffxl.cloud.model.SgUserExample;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.Page;

 /**
 * Generate By MBG 
 **/
public interface SgUserMapper extends GenericMapper<SgUser, SgUserExample, String> {
	/**
	 * 用户列表
	 * @param sgUser
	 * @param page
	 * @return
	 */
	List<SgUser> queryPageList(@Param("model")SgUser sgUser, @Param("page")Page page);
}
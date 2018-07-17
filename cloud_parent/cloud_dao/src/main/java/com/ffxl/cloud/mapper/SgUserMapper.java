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
	/**
	 * 我的消息的数量
	 * @param userId
	 * @return
	 */
	int queryMyInfo(@Param("userId")String userId);
	/**
	 * 我的消息的列表
	 * @param userId
	 * @return
	 */
	List<SgUser> queryMyInfoList(@Param("userId")String userId,@Param("page")Page page);
	/**
	 * token清空
	 * @param userId
	 * @return
	 */
	int updateTokenNull(@Param("userId")String userId);
}
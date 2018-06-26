package com.ffxl.cloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffxl.cloud.model.SysUser;
import com.ffxl.cloud.model.SysUserExample;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.Page;

 /**
 * Generate By MBG 
 **/
public interface SysUserMapper extends GenericMapper<SysUser, SysUserExample, String> {

	List<SysUser> queryPageList(@Param("model")SysUser sysUser, @Param("page")Page page);

	int updateStatus(@Param("idList")List<String> idList, @Param("status")int status);

	int deleteByIds(@Param("idList")List<String> idList);
}
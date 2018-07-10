package com.ffxl.cloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffxl.cloud.model.SysRole;
import com.ffxl.cloud.model.SysRoleExample;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.Page;

 /**
 * Generate By MBG 
 **/
public interface SysRoleMapper extends GenericMapper<SysRole, SysRoleExample, String> {
	/**
	 * 角色列表
	 * @param sysRole
	 * @param page
	 * @return
	 */
	List<SysRole> queryPageList(@Param("model")SysRole sysRole, @Param("page")Page page);
}
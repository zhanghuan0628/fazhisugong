package com.ffxl.cloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffxl.cloud.model.SysRole;
import com.ffxl.cloud.model.SysRoleExample;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.Page;
import com.ffxl.platform.core.node.ZTreeNode;

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
	/**
	 * 获取角色列表
	 * @return
	 */
	List<ZTreeNode> roleTreeList();
	/**
	 * 查询最大num
	 * @return
	 */
	int selectMaxNum();
	/**
	 * 删除该角色所有的权限
	 * @param roleId
	 * @return 
	 */
	int deleteRolesById(@Param("roleId")String roleId);
	/**
	 * 用户角色
	 * @param strArray
	 * @return
	 */
	List<ZTreeNode> roleTreeListByRoleId(@Param("strArray")String[] strArray);
}
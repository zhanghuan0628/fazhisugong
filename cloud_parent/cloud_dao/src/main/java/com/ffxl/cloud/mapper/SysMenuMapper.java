package com.ffxl.cloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffxl.cloud.model.SysMenu;
import com.ffxl.cloud.model.SysMenuExample;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.Page;
import com.ffxl.platform.core.node.ZTreeNode;

 /**
 * Generate By MBG 
 **/
public interface SysMenuMapper extends GenericMapper<SysMenu, SysMenuExample, String> {
	/**
	 * 根据条件查询菜单
	 * @param roleId
	 * @return
	 */
	List<SysMenu> getMenuIdsByRoleId(@Param("roleId")String roleId);
	/**
	 * 获取菜单列表树
	 * @return
	 */
	List<ZTreeNode> menuTreeList();
	/**
	 * 获取菜单列表树
	 * @param menuIds
	 * @return
	 */
	List<ZTreeNode> menuTreeListByMenuIds(List<SysMenu> menuIds);
	/**
	 * 查询菜单列表
	 * @param sysMenu
	 * @param page
	 * @return
	 */
	List<SysMenu> queryPageList(@Param("model")SysMenu sysMenu);
	/**
	 * 查询最大num
	 * @return
	 */
	int selectMaxNum();
	int selectMaxId();
}
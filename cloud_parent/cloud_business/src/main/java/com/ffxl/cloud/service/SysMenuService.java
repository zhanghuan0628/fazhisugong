package com.ffxl.cloud.service;

import java.util.List;

import com.ffxl.cloud.model.SysMenu;
import com.ffxl.cloud.model.SysMenuExample;
import com.ffxl.platform.core.GenericService;
import com.ffxl.platform.core.Page;
import com.ffxl.platform.core.node.ZTreeNode;

 /**
 * Generate By MBG 
 **/
public interface SysMenuService extends GenericService<SysMenu, SysMenuExample, String> {
     /**
     * According to the model information query object  
     * @param BaseSysMenu
     * @return 
     **/
    SysMenu queryByModel(SysMenu sysMenu);
    /**
     * 角色id
     * @param roleId
     * @return
     */
	List<SysMenu> getMenuIdsByRoleId(String roleId);
	/**
	 * 菜单列表
	 * @return
	 */
	List<ZTreeNode> menuTreeList();
	/**
	 * 角色id菜单列表
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
	List<SysMenu> queryPageList(SysMenu sysMenu);
	/**
	 * 查询最大num
	 * @return
	 */
	int selectMaxNum();
	/**
	 * 
	 * @return
	 */
	int selectMaxId();
}
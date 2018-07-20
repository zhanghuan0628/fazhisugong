package com.ffxl.cloud.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffxl.cloud.model.Menu;
import com.ffxl.cloud.model.MenuExample;
import com.ffxl.cloud.model.warpper.MenuWarpper;
import com.ffxl.platform.core.GenericService;
import com.ffxl.platform.core.node.MenuNode;

 /**
 * Generate By MBG 
 **/
public interface MenuService extends GenericService<Menu, MenuExample, String> {
     /**
     * According to the model information query object  
     * @param BaseMenu
     * @return 
     **/
    Menu queryByModel(Menu menu);

    /**
     * 获取资源url通过角色id
     * 
     * jaiwei
     * 2018年6月29日上午10:27:31
     * @param roleId 角色id
     * @return
     */
    List<String> getResUrlsByRoleId(String roleId);

    /**
     * 根据角色获取菜单
     * 
     * jaiwei
     * 2018年6月29日下午1:33:03
     * @param roleIds
     * @return
     */
    List<MenuNode> getMenusByRoleIds(List<String> roleIds);

    /**
     * 查询父菜单名及菜单名
     * jaiwei
     * 2018年7月20日上午11:43:15
     * @param code
     * @return
     */
    MenuWarpper queryMenuByCode(String code);
    
}
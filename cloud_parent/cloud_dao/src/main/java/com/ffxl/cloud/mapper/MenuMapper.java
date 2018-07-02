package com.ffxl.cloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffxl.cloud.model.Menu;
import com.ffxl.cloud.model.MenuExample;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.node.MenuNode;

 /**
 * Generate By MBG 
 **/
public interface MenuMapper extends GenericMapper<Menu, MenuExample, String> {

    /**
     * 获取资源url通过角色id
     * 
     * jaiwei
     * 2018年6月29日上午10:30:31
     * @param roleId 角色id
     * @return
     */
    List<String> getResUrlsByRoleId(@Param("roleId")String roleId);

    /**
     * 根据角色获取菜单
     * jaiwei
     * 2018年6月29日下午1:34:13
     * @param roleIds
     * @return
     */
    List<MenuNode> getMenusByRoleIds(List<String> roleIds);
}
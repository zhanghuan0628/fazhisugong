package com.ffxl.admin.core.util;

import java.util.ArrayList;
import java.util.List;

import com.ffxl.admin.core.common.constant.factory.ConstantFactory;
import com.ffxl.admin.core.shiro.ShiroKit;
import com.ffxl.admin.core.shiro.ShiroUser;
import com.ffxl.platform.core.node.MenuNode;

public class MenuKit {
    /**
     * 获取当前用户对应的权限下的菜单列表
     */
    public static List<MenuNode> getUserMenus() {
        
        ShiroUser shiroUser = ShiroKit.getUser();
        List<String> roleIds = shiroUser.getRoleList();
        List<MenuNode> menuList = new ArrayList<MenuNode>();
        menuList  = (List<MenuNode>) ConstantFactory.me().getUserMenus(roleIds);
        return menuList;
    }
}

package com.ffxl.admin.core.shiro.factory;

import java.util.List;

import org.apache.shiro.authc.SimpleAuthenticationInfo;

import com.ffxl.admin.core.shiro.ShiroUser;
import com.ffxl.cloud.model.SysUser;

/**
 * 定义shirorealm所需数据的接口
 * 
 * @author jiawei
 * 2018年6月28日
 */
public interface IShiro {

    /**
     * 根据账号获取登录用户
     *
     * @param loginName 登录账号
     */
    SysUser user(String loginName);

    /**
     * 根据系统用户获取Shiro的用户
     *
     * @param user 系统用户
     */
    ShiroUser shiroUser(SysUser user);

    /**
     * 获取权限列表通过角色id
     *
     * @param roleId 角色id
     */
    List<String> findPermissionsByRoleId(String roleId);

    /**
     * 根据角色id获取角色名称
     *
     * @param roleId 角色id
     */
    String findRoleNameByRoleId(String roleId);

    /**
     * 获取shiro的认证信息
     */
    SimpleAuthenticationInfo info(ShiroUser shiroUser, SysUser user, String realmName);

}

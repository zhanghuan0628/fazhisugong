package com.ffxl.admin.core.shiro.factory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.registry.infomodel.User;

import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ffxl.admin.core.common.constant.factory.ConstantFactory;
import com.ffxl.admin.core.shiro.ShiroUser;
import com.ffxl.admin.core.util.ApplicationContextUtils;
import com.ffxl.cloud.mapper.MenuMapper;
import com.ffxl.cloud.mapper.SysUserMapper;
import com.ffxl.cloud.model.SysUser;
import com.ffxl.cloud.service.MenuService;
import com.ffxl.cloud.service.SysUserService;
import com.ffxl.platform.constant.status.SysUserStatus;
import com.ffxl.platform.util.Convert;

@Service
@Transactional(readOnly = true)
public class ShiroFactroy implements IShiro {

    @Autowired
    private SysUserService userService;

    @Autowired
    private MenuService menuService;

    public static IShiro me() {
        return ApplicationContextUtils.getBean(IShiro.class);
    }

    @Override
    public SysUser user(String loginName) {
        SysUser quser = new SysUser();
        quser.setLoginName(loginName);
        SysUser user = userService.queryByModel(quser);
        // 账号不存在
        if (null == user) {
            throw new CredentialsException();
        }
        // 账号被禁用
        if (user.getStatus() != SysUserStatus.OK.getCode()) {
            throw new LockedAccountException();
        }
        return user;
    }

    @Override
    public ShiroUser shiroUser(SysUser user) {
        ShiroUser shiroUser = new ShiroUser();

        shiroUser.setId(user.getId());
        shiroUser.setLoginName((user.getLoginName()));
        shiroUser.setUserName(user.getUserName());

        String[] roleArray = Convert.toStrArray(",",user.getRoleId());
        List<String> roleList = new ArrayList<String>();
        List<String> roleNameList = new ArrayList<String>();
        for (String roleId : roleArray) {
            roleList.add(roleId);
            roleNameList.add(ConstantFactory.me().getSingleRoleName(roleId));
        }
        shiroUser.setRoleList(roleList);
        shiroUser.setRoleNames(roleNameList);

        return shiroUser;
    }

    @Override
    public List<String> findPermissionsByRoleId(String roleId) {
        return menuService.getResUrlsByRoleId(roleId);
    }

    @Override
    public String findRoleNameByRoleId(String roleId) {
        return ConstantFactory.me().getSingleRoleTip(roleId);
    }

    @Override
    public SimpleAuthenticationInfo info(ShiroUser shiroUser, SysUser user, String realmName) {
        String credentials = user.getLoginPassword();

        // 密码加盐处理
        String source = user.getSalt();
        ByteSource credentialsSalt = new Md5Hash(source);
        return new SimpleAuthenticationInfo(shiroUser, credentials, credentialsSalt, realmName);
    }

}

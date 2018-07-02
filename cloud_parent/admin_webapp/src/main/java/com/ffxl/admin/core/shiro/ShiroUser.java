package com.ffxl.admin.core.shiro;

import java.io.Serializable;
import java.util.List;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息
 * 
 * @author jiawei
 * 2018年6月28日
 */
public class ShiroUser implements Serializable {

    private static final long serialVersionUID = 1L;

    public String id;             // 主键ID
    public String loginName;       // 登录账号
    public String userName;        // 姓名
    public List<String> roleList; // 角色集
    public List<String> roleNames; // 角色名称集


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

   

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }

}

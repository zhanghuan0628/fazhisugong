package com.ffxl.admin.core.common.constant.dictmap;

import com.ffxl.admin.core.common.constant.dictmap.base.AbstractDictMap;

/**
 * 系统用户（管理员）的字典
 * @author jiawei
 * 2018年6月28日
 */
public class SysUserDic extends AbstractDictMap {

    @Override
    public void init() {
        put("id","主键id");
        put("loginName","登陆账号");
        put("loginPassword","登陆密码");
        put("userName","用户名称");
        put("sex","性别");
        put("email","电子邮件");
        put("cellphone","电话");
        put("roleId","角色名称");
    }

    @Override
    protected void initBeWrapped() {
        putFieldWrapperMethodName("sex","getSexName");
        putFieldWrapperMethodName("deptid","getDeptName");
        putFieldWrapperMethodName("roleid","getSingleRoleName");
        putFieldWrapperMethodName("userId","getUserAccountById");
        putFieldWrapperMethodName("roleIds","getRoleName");
    }
}

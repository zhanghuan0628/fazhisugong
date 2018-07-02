package com.ffxl.admin.core.common.constant.dictmap;

import com.ffxl.admin.core.common.constant.dictmap.base.AbstractDictMap;

public class SgUserDic extends AbstractDictMap{

	@Override
	public void init() {
		put("id","主键id");
        put("loginName","用户账号");
        put("password","密码");
        put("userName","用户名称");
        put("modifyPassword","是否修改密码");
        put("headUrl","头像");
        put("lastLoginDate","上次登录日期");
	}

	@Override
	protected void initBeWrapped() {
		
	}

}

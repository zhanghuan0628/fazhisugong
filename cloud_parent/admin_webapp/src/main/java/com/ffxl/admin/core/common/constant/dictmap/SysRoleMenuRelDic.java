package com.ffxl.admin.core.common.constant.dictmap;

import com.ffxl.admin.core.common.constant.dictmap.base.AbstractDictMap;

public class SysRoleMenuRelDic extends AbstractDictMap{

	@Override
	public void init() {
		put("id","主键id");
        put("roleId","角色id");
        put("menuId","菜单id");
	}

	@Override
	protected void initBeWrapped() {
		
	}

}

package com.ffxl.admin.core.common.constant.dictmap;

import com.ffxl.admin.core.common.constant.dictmap.base.AbstractDictMap;

public class SysMenuDic extends AbstractDictMap{

	@Override
	public void init() {
		put("id","主键id");
        put("code","编号");
        put("pcode","父编号");
        put("pcodes","编号数组");
        put("name","名称");
        put("icon","图标");
        put("url","路径");
        put("num","序号");
        put("levels","层级");
        put("menu","是否是菜单");
        put("tips","备注");
        put("status","状态");
        put("open","是否打开");
		
	}

	@Override
	protected void initBeWrapped() {
		
	}

}

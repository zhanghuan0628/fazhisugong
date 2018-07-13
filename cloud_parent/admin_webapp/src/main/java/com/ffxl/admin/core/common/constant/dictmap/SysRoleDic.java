package com.ffxl.admin.core.common.constant.dictmap;

import com.ffxl.admin.core.common.constant.dictmap.base.AbstractDictMap;

public class SysRoleDic extends AbstractDictMap{

	@Override
	public void init() {
		 	put("id","主键id");
	        put("num","排序");
	        put("pid","父id");
	        put("name","名称");
	        put("tip","备注");
	}

	@Override
	protected void initBeWrapped() {
		
	}

}

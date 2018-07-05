package com.ffxl.admin.core.common.constant.dictmap;

import com.ffxl.admin.core.common.constant.dictmap.base.AbstractDictMap;

public class DictionaryDic extends AbstractDictMap{

	@Override
	public void init() {
		put("id","主键id");
        put("num","序号");
        put("pid","父id");
        put("name","名称");
        put("tips","备注");
	}

	@Override
	protected void initBeWrapped() {
		
	}

}

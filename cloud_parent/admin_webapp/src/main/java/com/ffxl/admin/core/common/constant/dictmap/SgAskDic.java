package com.ffxl.admin.core.common.constant.dictmap;

import com.ffxl.admin.core.common.constant.dictmap.base.AbstractDictMap;

public class SgAskDic extends AbstractDictMap{

	@Override
	public void init() {
		put("id","主键id");
        put("title","标题");
        put("content","内容");
        put("userId","用户ID");
        put("createDate","创建日期");
        put("see","是否匿名");
        put("dummy","是否虚拟");
		
	}

	@Override
	protected void initBeWrapped() {
		
	}

}

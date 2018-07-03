package com.ffxl.admin.core.common.constant.dictmap;

import com.ffxl.admin.core.common.constant.dictmap.base.AbstractDictMap;

public class SgSubjectDic extends AbstractDictMap{

	@Override
	public void init() {
		put("id","主键id");
        put("questionJson","题目 ");
        put("createDate","创建时间");
        put("modifyDate","修改时间");
	}

	@Override
	protected void initBeWrapped() {
		
	}

}

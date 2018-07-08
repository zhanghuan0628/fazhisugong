package com.ffxl.admin.core.common.constant.dictmap;

import com.ffxl.admin.core.common.constant.dictmap.base.AbstractDictMap;

public class SgBannerDic extends AbstractDictMap{

	@Override
	public void init() {
		put("id","主键id");
        put("title","标题");
        put("sourceId","链接id");
        put("sortNum","排序");
        put("type","类型");
        put("bannerImg","图片");
        put("status","状态");
        put("name","名称");
	}

	@Override
	protected void initBeWrapped() {
		
	}

}

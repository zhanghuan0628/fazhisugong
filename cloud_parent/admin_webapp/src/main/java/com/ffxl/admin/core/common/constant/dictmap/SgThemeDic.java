package com.ffxl.admin.core.common.constant.dictmap;

import com.ffxl.admin.core.common.constant.dictmap.base.AbstractDictMap;

public class SgThemeDic extends AbstractDictMap{

	@Override
	public void init() {
		put("id","主键id");
        put("title","题目 ");
        put("content","内容");
        put("num","期数 ");
        put("startTime","开始时间");
        put("endTime","结束时间");
        put("subjectCounts","题数");
        put("createTime","创建时间 ");
        put("sore","每题分数");
        put("pass","及格题数 ");
        put("noPass","不及格题数 ");
        put("good","良好 ");
        put("fine","优秀 ");
	}

	@Override
	protected void initBeWrapped() {
		
	}

}

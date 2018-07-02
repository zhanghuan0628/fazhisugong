package com.ffxl.admin.core.common.constant.dictmap;

import com.ffxl.admin.core.common.constant.dictmap.base.AbstractDictMap;

public class SgLawDic extends AbstractDictMap{

	@Override
	public void init() {
		put("id","主键id");
        put("category","法制板块：板块目录在字典项中的category关键字law_information  :  法治动态  law_ lecture_room : 法制讲堂  law_magic :  苏供法宝law_risk : 法律风险 ");
        put("categoryCode","字典项中的code 需要结合法治板块查询字典项中的category才能得到板块目录的中文名称");
        put("num","排列序号");
        put("title","标题");
        put("content","内容");
        put("status","状态(publish：已发布  no_publish：未发布 ）");
        put("createBy","创建人的登录账号");
        put("createDate","创建日期");
        put("modifyBy","修改人的登录账号");
        put("modifyDate","修改日期");
	}

	@Override
	protected void initBeWrapped() {
		
	}
	

}

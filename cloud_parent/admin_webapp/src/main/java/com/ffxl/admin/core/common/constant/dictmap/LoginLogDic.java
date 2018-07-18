package com.ffxl.admin.core.common.constant.dictmap;

import com.ffxl.admin.core.common.constant.dictmap.base.AbstractDictMap;

public class LoginLogDic extends AbstractDictMap{

	@Override
	public void init() {
		put("id","主键id");
        put("logName","日志名称");
        put("loginName","登录人id");
        put("createTime","创建时间");
        put("succeed","成功信息");
        put("message","具体信息");
        put("ip","ip");
	}

	@Override
	protected void initBeWrapped() {
		
	}

}

package com.ffxl.admin.core.common.constant.dictmap;

import com.ffxl.admin.core.common.constant.dictmap.base.AbstractDictMap;

public class SgLawCommentDic extends AbstractDictMap{

	@Override
	public void init() {
		put("id","主键id");
        put("topicId","主题id ");
        put("topicType","主题类型   ask : 在线咨询   law : 法治在线");
        put("content","评论内容");
        put("fromUserType","评论人类型 sys：管理员 sg： 苏供用户");
        put("fromUserId","评论人Id");
        put("replyContent","回复内容");
        put("replyUserType","回复人类型 sys：管理员 sg： 苏供用户");
        put("replyUserId","回复人id");
        put("state","状态");
	}

	@Override
	protected void initBeWrapped() {
		
	}

}

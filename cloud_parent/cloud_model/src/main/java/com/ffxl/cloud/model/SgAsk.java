package com.ffxl.cloud.model;

import com.ffxl.cloud.model.base.BaseSgAsk;

public class SgAsk extends BaseSgAsk {
	private String createTime;//发布时间
	
	private String userName;//用户姓名

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
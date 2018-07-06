package com.ffxl.cloud.model;

import com.ffxl.cloud.model.base.BaseSgLawComment;

public class SgLawComment extends BaseSgLawComment {
	private String title;
	private String userName;
	private String createTime;
	private String replyId;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getReplyId() {
		return replyId;
	}
	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}
	
}
package com.ffxl.cloud.model;

import com.ffxl.cloud.model.base.BaseSgAsk;

public class SgAsk extends BaseSgAsk {
	private String createTime;//发布时间
	
	private String userName;//用户姓名
	
	private String replyContent;//回复内容
	
	private String topicId;
	
	private String commentId;

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

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
}
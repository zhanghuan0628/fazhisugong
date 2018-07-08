package com.ffxl.cloud.model;

import java.util.List;

import com.ffxl.cloud.model.base.BaseSgAsk;

public class SgAsk extends BaseSgAsk {
	private String createTime;//发布时间
	
	private String userName;//用户姓名
	
	private String replyContent;//回复内容
	
	private String topicId;
	
	private String commentId;
	
	private List backList;
	
	private String nickName;
	
	private String isAnswer;
	
	private String headUrl;

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

	public List getBackList() {
		return backList;
	}

	public void setBackList(List backList) {
		this.backList = backList;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getIsAnswer() {
		return isAnswer;
	}

	public void setIsAnswer(String isAnswer) {
		this.isAnswer = isAnswer;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}
}
package com.ffxl.cloud.model;

import java.util.Date;

import com.ffxl.cloud.model.base.BaseSgUser;

public class SgUser extends BaseSgUser {
	private String type;//类型
	private String topicId;//来源id
	private String replyName;//回复人名称
	private String headImg;//回复人头像
	private Date createDate;//回复时间
	private String fromContent;//问题
	private String replyContent;//回复内容
	private String readed;//1 已读 0未读
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	public String getReplyName() {
		return replyName;
	}
	public void setReplyName(String replyName) {
		this.replyName = replyName;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public String getFromContent() {
		return fromContent;
	}
	public void setFromContent(String fromContent) {
		this.fromContent = fromContent;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getReaded() {
		return readed;
	}
	public void setReaded(String readed) {
		this.readed = readed;
	}
	
}
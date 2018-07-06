package com.ffxl.cloud.model;

import com.ffxl.cloud.model.base.BaseSgAskComment;

public class SgAskComment extends BaseSgAskComment {
	private String qTitle;//问题标题
	private String qContent;//问题内容
	private String userName;//回答人名称
	public String getqTitle() {
		return qTitle;
	}
	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}
	public String getqContent() {
		return qContent;
	}
	public void setqContent(String qContent) {
		this.qContent = qContent;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
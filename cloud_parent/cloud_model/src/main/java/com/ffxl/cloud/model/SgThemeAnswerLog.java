package com.ffxl.cloud.model;

import java.util.List;

import com.ffxl.cloud.model.base.BaseSgThemeAnswerLog;

public class SgThemeAnswerLog extends BaseSgThemeAnswerLog {
	private String title;
	private String num;
	private String stage;
	private String userName;
	private String getAward;
	private List list;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGetAward() {
		return getAward;
	}
	public void setGetAward(String getAward) {
		this.getAward = getAward;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
}
package com.ffxl.cloud.model;

import com.ffxl.cloud.model.base.BaseSgThemeAwardLog;

public class SgThemeAwardLog extends BaseSgThemeAwardLog {
	private String num;
	private String score;
	private String rightNum;
	private String name;
	private String createTime;
	private String stage;
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getRightNum() {
		return rightNum;
	}
	public void setRightNum(String rightNum) {
		this.rightNum = rightNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	
}
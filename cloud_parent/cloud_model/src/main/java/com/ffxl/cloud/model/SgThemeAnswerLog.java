package com.ffxl.cloud.model;

import com.ffxl.cloud.model.base.BaseSgThemeAnswerLog;

public class SgThemeAnswerLog extends BaseSgThemeAnswerLog {
	private String title;
	private String num;
	private String stage;
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
}
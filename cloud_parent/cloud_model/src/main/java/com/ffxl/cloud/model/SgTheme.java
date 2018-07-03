package com.ffxl.cloud.model;

import com.ffxl.cloud.model.base.BaseSgTheme;

public class SgTheme extends BaseSgTheme {
	private String stage;//期数
	
	private int personNum;//人数

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public int getPersonNum() {
		return personNum;
	}

	public void setPersonNum(int personNum) {
		this.personNum = personNum;
	}
}
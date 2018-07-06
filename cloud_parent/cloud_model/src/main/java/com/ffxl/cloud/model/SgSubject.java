package com.ffxl.cloud.model;

import com.ffxl.cloud.model.base.BaseSgSubject;

public class SgSubject extends BaseSgSubject {
	private String qstn;//题目
	private String title;//选项
	private String result;//结果
	private int num;//选项数
	private String themeId;//期数id
	public String getQstn() {
		return qstn;
	}
	public void setQstn(String qstn) {
		this.qstn = qstn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getThemeId() {
		return themeId;
	}
	public void setThemeId(String themeId) {
		this.themeId = themeId;
	}
}
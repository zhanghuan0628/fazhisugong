package com.ffxl.cloud.model;

import java.util.List;
import java.util.Map;

import com.ffxl.cloud.model.base.BaseSgSubject;

public class SgSubject extends BaseSgSubject {
	private String qstn;//题目
	private String title;//选项
	private String result;//结果
	private int num;//选项数
	private String themeId;//期数id
	private List qstnList;
	private Map qstnMap;
	private String choose;
	private String question_id;
	private String correct;
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
	public List getQstnList() {
		return qstnList;
	}
	public void setQstnList(List qstnList) {
		this.qstnList = qstnList;
	}
	public Map getQstnMap() {
		return qstnMap;
	}
	public void setQstnMap(Map qstnMap) {
		this.qstnMap = qstnMap;
	}
	public String getChoose() {
		return choose;
	}
	public void setChoose(String choose) {
		this.choose = choose;
	}
	public String getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(String question_id) {
		this.question_id = question_id;
	}
	public String getCorrect() {
		return correct;
	}
	public void setCorrect(String correct) {
		this.correct = correct;
	}
}
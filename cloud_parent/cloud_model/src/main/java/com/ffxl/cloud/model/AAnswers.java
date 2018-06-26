package com.ffxl.cloud.model;

import java.util.ArrayList;
import java.util.List;

import com.ffxl.cloud.model.base.BaseAAnswers;

public class AAnswers extends BaseAAnswers {
	
	private static final long serialVersionUID = 8655044733538730294L;
	private String nickName;
	private String headImg;
	private int praises;
	private String dotoName;
	private String isPraise;
	private String sortName;
	private String roleName;
	private String sex;
	private String questionContent;
	private String questionVoiceSrc;
	private String questionVoiceLength;
	private String questionRole;
	private String questionSex;
	private String questionUserBaseId;
	private String answerDateBegin;
	private String answerDateEnd;
	private String answerUserId;
	private boolean read;

	
	public String getIsPraise() {
		return isPraise;
	}

	public void setIsPraise(String isPraise) {
		this.isPraise = isPraise;
	}

	private List<AAnswers> childList = new ArrayList<AAnswers>();

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public int getPraises() {
		return praises;
	}

	public void setPraises(int praises) {
		this.praises = praises;
	}

	public String getDotoName() {
		return dotoName;
	}

	public List<AAnswers> getChildList() {
		return childList;
	}

	public void setChildList(List<AAnswers> childList) {
		this.childList = childList;
	}

	public void setDotoName(String dotoName) {
		this.dotoName = dotoName;
	}

//	public int getIsPraise() {
//		return isPraise;
//	}
//
//	public void setIsPraise(int isPraise) {
//		this.isPraise = isPraise;
//	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public String getAnswerDateBegin() {
		return answerDateBegin;
	}

	public void setAnswerDateBegin(String answerDateBegin) {
		this.answerDateBegin = answerDateBegin;
	}

	public String getAnswerDateEnd() {
		return answerDateEnd;
	}

	public void setAnswerDateEnd(String answerDateEnd) {
		this.answerDateEnd = answerDateEnd;
	}

	public String getAnswerUserId() {
		return answerUserId;
	}

	public void setAnswerUserId(String answerUserId) {
		this.answerUserId = answerUserId;
	}

	public String getQuestionVoiceSrc() {
		return questionVoiceSrc;
	}

	public void setQuestionVoiceSrc(String questionVoiceSrc) {
		this.questionVoiceSrc = questionVoiceSrc;
	}

	public String getQuestionVoiceLength() {
		return questionVoiceLength;
	}

	public void setQuestionVoiceLength(String questionVoiceLength) {
		this.questionVoiceLength = questionVoiceLength;
	}

	public String getQuestionRole() {
		return questionRole;
	}

	public void setQuestionRole(String questionRole) {
		this.questionRole = questionRole;
	}

	public String getQuestionSex() {
		return questionSex;
	}

	public void setQuestionSex(String questionSex) {
		this.questionSex = questionSex;
	}

	public String getQuesstionUserBaseId() {
		return questionUserBaseId;
	}

	public void setQuesstionUserBaseId(String quesstionUserBaseId) {
		this.questionUserBaseId = quesstionUserBaseId;
	}

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
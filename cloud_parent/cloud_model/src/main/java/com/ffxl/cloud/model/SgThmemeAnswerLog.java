package com.ffxl.cloud.model;

import com.ffxl.cloud.model.base.BaseSgThmemeAnswerLog;

public class SgThmemeAnswerLog extends BaseSgThmemeAnswerLog {
	private int num;//及格次数
	
	private String avgScore;//平均分
	
	private String maxScore;//最高分

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(String avgScore) {
		this.avgScore = avgScore;
	}

	public String getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(String maxScore) {
		this.maxScore = maxScore;
	}
}
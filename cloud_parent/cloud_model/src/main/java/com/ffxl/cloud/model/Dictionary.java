package com.ffxl.cloud.model;

import com.ffxl.cloud.model.base.BaseDictionary;

public class Dictionary extends BaseDictionary {
	private String code;
	private Double prob;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getProb() {
		return prob;
	}

	public void setProb(Double prob) {
		this.prob = prob;
	}
}
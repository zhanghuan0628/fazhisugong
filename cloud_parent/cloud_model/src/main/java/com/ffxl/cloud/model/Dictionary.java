package com.ffxl.cloud.model;

import com.ffxl.cloud.model.base.BaseDictionary;

public class Dictionary extends BaseDictionary {
	private String code;
	private Double prob;
	private String dictName;

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

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
}
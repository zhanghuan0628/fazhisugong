package com.ffxl.cloud.model;

import com.ffxl.cloud.model.base.BaseSgLaw;

public class SgLaw extends BaseSgLaw {
	private int sort;//排序
	
	private int count;//收藏人数
	
	private String createTime;//发布时间
	
	private String type;//类型
	
	private String detail;
	
	private String name;//专业名称

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
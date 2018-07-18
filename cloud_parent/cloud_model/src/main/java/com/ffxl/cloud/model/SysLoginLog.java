package com.ffxl.cloud.model;

import com.ffxl.cloud.model.base.BaseSysLoginLog;

public class SysLoginLog extends BaseSysLoginLog {
	private String name;
	private String createDate;
	private String datemax;
	private String datemin;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getDatemax() {
		return datemax;
	}
	public void setDatemax(String datemax) {
		this.datemax = datemax;
	}
	public String getDatemin() {
		return datemin;
	}
	public void setDatemin(String datemin) {
		this.datemin = datemin;
	}
}
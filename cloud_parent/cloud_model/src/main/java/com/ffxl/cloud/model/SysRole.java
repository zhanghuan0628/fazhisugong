package com.ffxl.cloud.model;

import com.ffxl.cloud.model.base.BaseSysRole;

public class SysRole extends BaseSysRole {
	private String fatherName;
	private boolean open;

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
}
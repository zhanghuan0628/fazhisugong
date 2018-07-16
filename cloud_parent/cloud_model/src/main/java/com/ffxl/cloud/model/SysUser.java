package com.ffxl.cloud.model;

import com.ffxl.cloud.model.base.BaseSysUser;

public class SysUser extends BaseSysUser {
	private String roles;

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
}
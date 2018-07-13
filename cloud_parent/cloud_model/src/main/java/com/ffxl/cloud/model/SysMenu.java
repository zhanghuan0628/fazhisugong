package com.ffxl.cloud.model;

import com.ffxl.cloud.model.base.BaseSysMenu;

public class SysMenu extends BaseSysMenu {
	private String menuId;
	private String menuName;
	private String state;
	private String pname;
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}
}
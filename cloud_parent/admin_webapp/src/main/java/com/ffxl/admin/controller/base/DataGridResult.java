package com.ffxl.admin.controller.base;

import java.util.List;

/**
 * @author wsx
 * 
 */
public class DataGridResult {

	public static DataGridResult build(List list) {
		DataGridResult result = new DataGridResult();
		result.rows = list;
		result.total = list.size();
		return result;
	}

	private List rows;
	private int total;

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}

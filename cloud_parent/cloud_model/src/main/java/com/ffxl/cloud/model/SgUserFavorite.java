package com.ffxl.cloud.model;

import com.ffxl.cloud.model.base.BaseSgUserFavorite;

public class SgUserFavorite extends BaseSgUserFavorite {
	private String imgUrl;
	
	private String title;
	
	private String content;
	
	private int sortNum;
	

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}
}
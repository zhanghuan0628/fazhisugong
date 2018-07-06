package com.ffxl.cloud.model;

import java.util.Map;

import com.ffxl.cloud.model.base.BaseSgLaw;

public class SgLaw extends BaseSgLaw {
	private int sort;//排序
	
	private int count;//收藏人数
	
	private String createTime;//发布时间
	
	private String detail;
	
	private String name;//专业名称
	
	private String favorite;//是否已收藏
	
	private String chapter;//章节
	
	private Map<String,String> map;
	
	private String allChapter;

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

	public String getFavorite() {
		return favorite;
	}

	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}

	public Map<String,String> getMap() {
		return map;
	}

	public void setMap(Map<String,String> map) {
		this.map = map;
	}

	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	public String getAllChapter() {
		return allChapter;
	}

	public void setAllChapter(String allChapter) {
		this.allChapter = allChapter;
	}
}
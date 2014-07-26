package com.lps.edc.dto;

import java.util.List;

public class TableDto {
	private List<Object> title = null;
	private List<Object> data = null;
	public List<Object> getTitle() {
		return title;
	}
	public List<Object> getData() {
		return data;
	}
	public void setTitle(List<Object> title) {
		this.title = title;
	}
	public void setData(List<Object> data) {
		this.data = data;
	}
}

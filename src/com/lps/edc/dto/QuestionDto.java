package com.lps.edc.dto;

import java.util.ArrayList;
import java.util.List;

public class QuestionDto {
	private String id;
	private String name;
	private int order;
	private String show;
	private List<QuestionDto> child = new ArrayList<QuestionDto>();
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getShow() {
		return show;
	}
	public List<QuestionDto> getChild() {
		return child;
	}
	public int getOrder() {
		return order;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public void setChild(List<QuestionDto> child) {
		this.child = child;
	}
	public void setOrder(int order) {
		this.order = order;
	}
}

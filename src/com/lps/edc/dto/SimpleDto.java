package com.lps.edc.dto;

public class SimpleDto {
	private String id;
	private String name;
	private String state;
	public SimpleDto() {
	}
	public SimpleDto(String id, String name) {
		this.id = id;
		this.name = name;
	}
	public SimpleDto(String id, String name, String state) {
		this.id = id;
		this.name = name;
		this.state = state;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getState() {
		return state;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setState(String state) {
		this.state = state;
	}
}

package com.lps.edc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="statistical_category")
public class StatisticalCategory {
	private int id;
	private String name;
	private String code;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	@Column
	public String getName() {
		return name;
	}
	@Column
	public String getCode() {
		return code;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCode(String code) {
		this.code = code;
	}
}

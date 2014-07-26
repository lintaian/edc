package com.lps.edc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 数据字典
 * @author lta
 *
 */
@Entity
@Table(name="sys_word")
public class SysWord implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String wordType;
	private String wordInfo;
	private Integer parentid;
	private String wordId;
	private String extent;

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "WordType")
	public String getWordType() {
		return this.wordType;
	}

	public void setWordType(String wordType) {
		this.wordType = wordType;
	}

	@Column(name = "WordInfo")
	public String getWordInfo() {
		return this.wordInfo;
	}

	public void setWordInfo(String wordInfo) {
		this.wordInfo = wordInfo;
	}

	@Column(name = "Parentid")
	public Integer getParentid() {
		return this.parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	@Column(name = "WordId", nullable = false, length = 36)
	public String getWordId() {
		return this.wordId;
	}

	public void setWordId(String wordId) {
		this.wordId = wordId;
	}

	@Column(name = "extent")
	public String getExtent() {
		return this.extent;
	}

	public void setExtent(String extent) {
		this.extent = extent;
	}

}

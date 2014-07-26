package com.lps.edc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 考试批次
 * @author lta
 *
 */
@Entity
@Table(name="inf_exam_batch")
public class InfExamBatch {
	private int id;
	private String schoolId;
	private String batchId;
	private String batchName;
	private Date createTime;
	private String createUser;
	private String description;
	private String createUserName;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	@Column
	public String getSchoolId() {
		return schoolId;
	}
	@Column
	public String getBatchId() {
		return batchId;
	}
	@Column
	public String getBatchName() {
		return batchName;
	}
	@Column
	public Date getCreateTime() {
		return createTime;
	}
	@Column
	public String getCreateUser() {
		return createUser;
	}
	@Column
	public String getDescription() {
		return description;
	}
	@Column
	public String getCreateUserName() {
		return createUserName;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
}

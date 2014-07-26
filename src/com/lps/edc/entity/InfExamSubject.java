package com.lps.edc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 考试科目
 * @author lta
 *
 */
@Entity
@Table(name = "Inf_Exam_Subject")
public class InfExamSubject implements java.io.Serializable {

	private static final long serialVersionUID = 5789590389723346459L;
	private int id;
	private String batchId;
	private String subjectId;
	private String examId;
	private Date inserttime;
	private Date examTime;
	private String createUserId;
	private String createUserName;
	private String checkUserId;
	private String checkUserName;
	private String extent;
	private String examName;
	private String state;
	private Date checkTime;

	@Id
	@GeneratedValue
	@Column(name = "Id", nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "BatchId", length = 50)
	public String getBatchId() {
		return this.batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	@Column(name = "SubjectId", length = 50)
	public String getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	@Column(name = "ExamId", nullable = false, length = 50)
	public String getExamId() {
		return this.examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	@Column(name = "Inserttime", nullable = false, length = 23)
	public Date getInserttime() {
		return this.inserttime;
	}

	public void setInserttime(Date inserttime) {
		this.inserttime = inserttime;
	}

	@Column(name = "ExamTime", nullable = false, length = 23)
	public Date getExamTime() {
		return this.examTime;
	}

	public void setExamTime(Date examTime) {
		this.examTime = examTime;
	}

	@Column(name = "CreateUserId", nullable = false, length = 50)
	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	@Column(name = "CreateUserName", length = 50)
	public String getCreateUserName() {
		return this.createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	@Column(name = "CheckUserId", nullable = false, length = 50)
	public String getCheckUserId() {
		return this.checkUserId;
	}

	public void setCheckUserId(String checkUserId) {
		this.checkUserId = checkUserId;
	}

	@Column(name = "CheckUserName", length = 50)
	public String getCheckUserName() {
		return this.checkUserName;
	}

	public void setCheckUserName(String checkUserName) {
		this.checkUserName = checkUserName;
	}

	@Column(name = "Extent")
	public String getExtent() {
		return this.extent;
	}

	public void setExtent(String extent) {
		this.extent = extent;
	}

	@Column(name = "ExamName")
	public String getExamName() {
		return this.examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	@Column(name = "state", length = 50)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "CheckTime", length = 23)
	public Date getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
}

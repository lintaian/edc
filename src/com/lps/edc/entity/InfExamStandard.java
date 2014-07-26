package com.lps.edc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="inf_exam_standard")
public class InfExamStandard {
	private int id;
	private String schoolId;
	private String teacherId;
	private String subjectId;
	private String subjectTypeId;
	private String standardTypeId;
	private double score;
	private Date updateTime;
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
	public String getTeacherId() {
		return teacherId;
	}
	@Column
	public String getSubjectId() {
		return subjectId;
	}
	@Column
	public String getSubjectTypeId() {
		return subjectTypeId;
	}
	@Column
	public String getStandardTypeId() {
		return standardTypeId;
	}
	@Column
	public double getScore() {
		return score;
	}
	@Column
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public void setSubjectTypeId(String subjectTypeId) {
		this.subjectTypeId = subjectTypeId;
	}
	public void setStandardTypeId(String standardTypeId) {
		this.standardTypeId = standardTypeId;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}

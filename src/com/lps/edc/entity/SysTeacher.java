package com.lps.edc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 教师
 * @author lta
 *
 */
@Entity
@Table(name = "Sys_Teacher")
public class SysTeacher implements java.io.Serializable {

	private static final long serialVersionUID = 3965790282486022412L;
	private int id;
	private String teacherid;
	private String teachername;
	private String subjectid;
	private String password;
	private String schoolId;
	private int viewAllSubjects;
	private String sex;
	private String state;

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "teacherid", nullable = false)
	public String getTeacherid() {
		return this.teacherid;
	}

	public void setTeacherid(String teacherid) {
		this.teacherid = teacherid;
	}

	@Column(name = "teachername")
	public String getTeachername() {
		return this.teachername;
	}

	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}

	@Column(name = "subjectid", length = 50)
	public String getSubjectid() {
		return this.subjectid;
	}

	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}

	@Column(name = "password", length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "schoolId")
	public String getSchoolId() {
		return this.schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	@Column(name = "ViewAllSubjects", nullable = false)
	public int getViewAllSubjects() {
		return this.viewAllSubjects;
	}

	public void setViewAllSubjects(int viewAllSubjects) {
		this.viewAllSubjects = viewAllSubjects;
	}

	@Column(name = "Sex", length = 10)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "state", length = 10)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}

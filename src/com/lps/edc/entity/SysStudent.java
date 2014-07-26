package com.lps.edc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 学生
 * @author lta
 *
 */
@Entity
@Table(name = "Sys_Student")
public class SysStudent implements java.io.Serializable {

	private static final long serialVersionUID = -4091143078589598488L;
	private String studentId;
	private int id;
	private String schoolId;
	private String gradeId;
	private String classId;
	private String studentNo;
	private String studentName;
	private String state;
	private String sex;
	private String nation;
	private String idcard;
	private String schoolNo;
	private String examNo;
	private String census;
	private String studyState;
	private Integer enterScore;
	private String remark;

	@Id
	@Column(name = "StudentID", unique = true, nullable = false, length = 36)
	public String getStudentId() {
		return this.studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	@Column(name = "ID", nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "SchoolID", nullable = false, length = 36)
	public String getSchoolId() {
		return this.schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	@Column(name = "GradeID", nullable = false, length = 36)
	public String getGradeId() {
		return this.gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	@Column(name = "ClassID", nullable = false, length = 36)
	public String getClassId() {
		return this.classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	@Column(name = "StudentNo", length = 50)
	public String getStudentNo() {
		return this.studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	@Column(name = "StudentName", nullable = false, length = 32)
	public String getStudentName() {
		return this.studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	@Column(name = "State", nullable = false, length = 10)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "Sex", length = 2)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "Nation", length = 20)
	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	@Column(name = "IDCard", length = 20)
	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	@Column(name = "SchoolNo", length = 50)
	public String getSchoolNo() {
		return this.schoolNo;
	}

	public void setSchoolNo(String schoolNo) {
		this.schoolNo = schoolNo;
	}

	@Column(name = "ExamNo", nullable = false, length = 50)
	public String getExamNo() {
		return this.examNo;
	}

	public void setExamNo(String examNo) {
		this.examNo = examNo;
	}

	@Column(name = "Census", length = 200)
	public String getCensus() {
		return this.census;
	}

	public void setCensus(String census) {
		this.census = census;
	}

	@Column(name = "StudyState", length = 50)
	public String getStudyState() {
		return this.studyState;
	}

	public void setStudyState(String studyState) {
		this.studyState = studyState;
	}

	@Column(name = "EnterScore")
	public Integer getEnterScore() {
		return this.enterScore;
	}

	public void setEnterScore(Integer enterScore) {
		this.enterScore = enterScore;
	}

	@Column(name = "Remark", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}

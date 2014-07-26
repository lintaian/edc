package com.lps.edc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 学校，班级关联表
 * @author lta
 *
 */
@Entity
@Table(name="inf_school_class")
public class InfSchoolClass {
	private int id;
	private String schoolId;
	private String gradeId;
	private String classGroupId;
	private String classId;
	private String classTerchaerId;
	private String wlTypeId;
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
	public String getGradeId() {
		return gradeId;
	}
	@Column
	public String getClassGroupId() {
		return classGroupId;
	}
	@Column
	public String getClassId() {
		return classId;
	}
	@Column
	public String getClassTerchaerId() {
		return classTerchaerId;
	}
	@Column
	public String getWlTypeId() {
		return wlTypeId;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	public void setClassGroupId(String classGroupId) {
		this.classGroupId = classGroupId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public void setClassTerchaerId(String classTerchaerId) {
		this.classTerchaerId = classTerchaerId;
	}
	public void setWlTypeId(String wlTypeId) {
		this.wlTypeId = wlTypeId;
	}
	
}

package com.lps.edc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 学校
 * @author lta
 *
 */
@Entity
@Table(name="sys_school")
public class SysSchool {
	private int id;
	private String schoolId;
	private String schoolName;
	private String remark;
	private String state;
	private String area;
	private String schoolLeaderId;
	private String telphone;
	private String address;
	private Date insertTime;
	private String province;
	private String city;
	@Column
	@GeneratedValue
	public int getId() {
		return id;
	}
	@Id
	@Column(name="schoolid")
	public String getSchoolId() {
		return schoolId;
	}
	@Column
	public String getSchoolName() {
		return schoolName;
	}
	@Column
	public String getRemark() {
		return remark;
	}
	@Column
	public String getState() {
		return state;
	}
	@Column
	public String getArea() {
		return area;
	}
	@Column
	public String getSchoolLeaderId() {
		return schoolLeaderId;
	}
	@Column
	public String getTelphone() {
		return telphone;
	}
	@Column
	public String getAddress() {
		return address;
	}
	@Column
	public Date getInsertTime() {
		return insertTime;
	}
	@Column
	public String getProvince() {
		return province;
	}
	@Column
	public String getCity() {
		return city;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public void setSchoolLeaderId(String schoolLeaderId) {
		this.schoolLeaderId = schoolLeaderId;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public void setCity(String city) {
		this.city = city;
	}
}

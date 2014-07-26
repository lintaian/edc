package com.lps.edc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 用户
 * @author lta
 *
 */
@Entity
@Table(name="sys_user")
public class SysUser {
	private int id;
	private String userName;
	private String userId;
	private String password;
	private String mobile;
	private String tel;
	private String address;
	private String email;
	private Date regTime;
	private int regType;
	@Id
	public int getId() {
		return id;
	}
	@Column
	public String getUserName() {
		return userName;
	}
	@Column
	public String getUserId() {
		return userId;
	}
	@Column
	public String getPassword() {
		return password;
	}
	@Column
	public String getMobile() {
		return mobile;
	}
	@Column
	public String getTel() {
		return tel;
	}
	@Column
	public String getAddress() {
		return address;
	}
	@Column
	public String getEmail() {
		return email;
	}
	@Column
	public Date getRegTime() {
		return regTime;
	}
	@Column
	public int getRegType() {
		return regType;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public void setRegType(int regType) {
		this.regType = regType;
	}
}

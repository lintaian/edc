package com.lps.edc.dto;

public class ClassStructDto {
	 private String schoolid;
     private String schoolname;
     private String gradeid;
     private String gradename;
     private String classid;
     private String classname;
     private String classgroupid;
     private String classgroupname;
     private String wltypeid;
     private String wltypename;
     
     public ClassStructDto(String schoolid, String schoolname, String gradeid,
			String gradename, String classid, String classname,
			String classgroupid, String classgroupname, String wltypeid,
			String wltypename) {
		super();
		this.schoolid = schoolid;
		this.schoolname = schoolname;
		this.gradeid = gradeid;
		this.gradename = gradename;
		this.classid = classid;
		this.classname = classname;
		this.classgroupid = classgroupid;
		this.classgroupname = classgroupname;
		this.wltypeid = wltypeid;
		this.wltypename = wltypename;
	}
	public String getSchoolid() {
		return schoolid;
	}
	public String getSchoolname() {
		return schoolname;
	}
	public String getGradeid() {
		return gradeid;
	}
	public String getGradename() {
		return gradename;
	}
	public String getClassid() {
		return classid;
	}
	public String getClassname() {
		return classname;
	}
	public String getClassgroupid() {
		return classgroupid;
	}
	public String getClassgroupname() {
		return classgroupname;
	}
	public String getWltypeid() {
		return wltypeid;
	}
	public String getWltypename() {
		return wltypename;
	}
	public void setSchoolid(String schoolid) {
		this.schoolid = schoolid;
	}
	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}
	public void setGradeid(String gradeid) {
		this.gradeid = gradeid;
	}
	public void setGradename(String gradename) {
		this.gradename = gradename;
	}
	public void setClassid(String classid) {
		this.classid = classid;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public void setClassgroupid(String classgroupid) {
		this.classgroupid = classgroupid;
	}
	public void setClassgroupname(String classgroupname) {
		this.classgroupname = classgroupname;
	}
	public void setWltypeid(String wltypeid) {
		this.wltypeid = wltypeid;
	}
	public void setWltypename(String wltypename) {
		this.wltypename = wltypename;
	}
}

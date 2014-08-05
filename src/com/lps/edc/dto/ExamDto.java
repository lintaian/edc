package com.lps.edc.dto;

public class ExamDto {
	private String batchid;
    private String batchname;
    private String examid;
    private String examname;
    private String subjectid;
    private String subjectname;
    private String schoolname;
    private String examtime;
    private String standscore1;
    private String standscore2;
    public ExamDto() {}
    
	public ExamDto(String batchid, String batchname, String examid,
			String examname, String subjectid, String subjectname,
			String schoolname, String examtime, String standscore1,
			String standscore2) {
		super();
		this.batchid = batchid;
		this.batchname = batchname;
		this.examid = examid;
		this.examname = examname;
		this.subjectid = subjectid;
		this.subjectname = subjectname;
		this.schoolname = schoolname;
		this.examtime = examtime;
		this.standscore1 = standscore1;
		this.standscore2 = standscore2;
	}

	public String getBatchid() {
		return batchid;
	}
	public String getBatchname() {
		return batchname;
	}
	public String getExamid() {
		return examid;
	}
	public String getExamname() {
		return examname;
	}
	public String getSubjectid() {
		return subjectid;
	}
	public String getSubjectname() {
		return subjectname;
	}
	public String getSchoolname() {
		return schoolname;
	}
	public String getExamtime() {
		return examtime;
	}
	public String getStandscore1() {
		return standscore1;
	}
	public String getStandscore2() {
		return standscore2;
	}
	public void setBatchid(String batchid) {
		this.batchid = batchid;
	}
	public void setBatchname(String batchname) {
		this.batchname = batchname;
	}
	public void setExamid(String examid) {
		this.examid = examid;
	}
	public void setExamname(String examname) {
		this.examname = examname;
	}
	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}
	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}
	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}
	public void setExamtime(String examtime) {
		this.examtime = examtime;
	}
	public void setStandscore1(String standscore1) {
		this.standscore1 = standscore1;
	}
	public void setStandscore2(String standscore2) {
		this.standscore2 = standscore2;
	}
}

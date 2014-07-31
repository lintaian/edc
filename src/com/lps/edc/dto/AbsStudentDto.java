package com.lps.edc.dto;

public class AbsStudentDto {
	private int id;
	private String examNo;
	private String studentName;
	public AbsStudentDto() {
	}
	public AbsStudentDto(int id, String examNo, String studentName) {
		this.id = id;
		this.examNo = examNo;
		this.studentName = studentName;
	}
	public int getId() {
		return id;
	}
	public String getExamNo() {
		return examNo;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setExamNo(String examNo) {
		this.examNo = examNo;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
}

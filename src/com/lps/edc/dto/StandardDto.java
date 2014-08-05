package com.lps.edc.dto;

public class StandardDto {
	private String subjectId;
	private String subjectName;
	private String subjectTypeId;
	private String subjectTypeName;
	private int standardId1;
	private int standardId2;
	private double score1;
	private double score2;
	private String standardTypeName1;
	private String standardTypeName2;
	public String getSubjectName() {
		return subjectName;
	}
	public String getSubjectTypeName() {
		return subjectTypeName;
	}
	public double getScore1() {
		return score1;
	}
	public double getScore2() {
		return score2;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public void setSubjectTypeName(String subjectTypeName) {
		this.subjectTypeName = subjectTypeName;
	}
	public void setScore1(double score1) {
		this.score1 = score1;
	}
	public void setScore2(double score2) {
		this.score2 = score2;
	}
	public int getStandardId1() {
		return standardId1;
	}
	public int getStandardId2() {
		return standardId2;
	}
	public void setStandardId1(int standardId1) {
		this.standardId1 = standardId1;
	}
	public void setStandardId2(int standardId2) {
		this.standardId2 = standardId2;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public String getSubjectTypeId() {
		return subjectTypeId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public void setSubjectTypeId(String subjectTypeId) {
		this.subjectTypeId = subjectTypeId;
	}
	public String getStandardTypeName1() {
		return standardTypeName1;
	}
	public String getStandardTypeName2() {
		return standardTypeName2;
	}
	public void setStandardTypeName1(String standardTypeName1) {
		this.standardTypeName1 = standardTypeName1;
	}
	public void setStandardTypeName2(String standardTypeName2) {
		this.standardTypeName2 = standardTypeName2;
	}
}

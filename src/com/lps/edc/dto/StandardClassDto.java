package com.lps.edc.dto;

public class StandardClassDto {
	private String subjectid;
	private String WLtype;
	private String StandardType;
    private Double StandardScore;

    public StandardClassDto(String subjectid, String wLtype,
			String standardType, Double standardScore) {
		super();
		this.subjectid = subjectid;
		WLtype = wLtype;
		StandardType = standardType;
		StandardScore = standardScore;
	}
	public String getSubjectid() {
		return subjectid;
	}
	public String getWLtype() {
		return WLtype;
	}
	public String getStandardType() {
		return StandardType;
	}
	public Double getStandardScore() {
		return StandardScore;
	}
	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}
	public void setWLtype(String wLtype) {
		WLtype = wLtype;
	}
	public void setStandardType(String standardType) {
		StandardType = standardType;
	}
	public void setStandardScore(Double standardScore) {
		StandardScore = standardScore;
	}
}

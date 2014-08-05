package com.lps.edc.dto;

import java.util.List;

public class RespDto {
	private List<ClassStructDto> classlist;
    private List<ExamDto> examlist;
    private List<TongJiProDto> prolist;
    private List<StandardClassDto> standlist;
    public RespDto() {}
	public RespDto(List<ClassStructDto> classlist, List<ExamDto> examlist,
			List<TongJiProDto> prolist, List<StandardClassDto> standlist) {
		super();
		this.classlist = classlist;
		this.examlist = examlist;
		this.prolist = prolist;
		this.standlist = standlist;
	}
	public List<ClassStructDto> getClasslist() {
		return classlist;
	}
	public List<ExamDto> getExamlist() {
		return examlist;
	}
	public List<TongJiProDto> getProlist() {
		return prolist;
	}
	public List<StandardClassDto> getStandlist() {
		return standlist;
	}
	public void setClasslist(List<ClassStructDto> classlist) {
		this.classlist = classlist;
	}
	public void setExamlist(List<ExamDto> examlist) {
		this.examlist = examlist;
	}
	public void setProlist(List<TongJiProDto> prolist) {
		this.prolist = prolist;
	}
	public void setStandlist(List<StandardClassDto> standlist) {
		this.standlist = standlist;
	}
}

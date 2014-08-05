package com.lps.edc.dto;

public class TongJiProDto {
	private String proname;
    private String proclass;
    
	public TongJiProDto(String proname, String proclass) {
		super();
		this.proname = proname;
		this.proclass = proclass;
	}
	public String getProname() {
		return proname;
	}
	public String getProclass() {
		return proclass;
	}
	public void setProname(String proname) {
		this.proname = proname;
	}
	public void setProclass(String proclass) {
		this.proclass = proclass;
	}
}

package com.lps.edc.util;

import org.springframework.stereotype.Repository;

@Repository
public class ReportConfig {
	private String url;

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}

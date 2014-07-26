package com.lps.edc.util;

import org.springframework.stereotype.Repository;

@Repository
public class ImageGetConfig {
	private String imageGetUrl;
	private String imageGetMethod;
	private String fmt;
	public String getImageGetUrl() {
		return imageGetUrl;
	}
	public String getImageGetMethod() {
		return imageGetMethod;
	}
	public String getFmt() {
		return fmt;
	}
	public void setImageGetUrl(String imageGetUrl) {
		this.imageGetUrl = imageGetUrl;
	}
	public void setImageGetMethod(String imageGetMethod) {
		this.imageGetMethod = imageGetMethod;
	}
	public void setFmt(String fmt) {
		this.fmt = fmt;
	}
}

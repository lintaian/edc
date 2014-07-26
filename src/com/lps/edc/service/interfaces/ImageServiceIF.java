package com.lps.edc.service.interfaces;

public interface ImageServiceIF {
//	public void login() throws Exception;
	public byte[] getOneExamQuestion(String imageId, String questionId) throws Exception;
}

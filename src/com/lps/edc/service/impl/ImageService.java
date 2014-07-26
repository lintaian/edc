package com.lps.edc.service.impl;

import javax.annotation.Resource;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.lps.edc.service.interfaces.ImageServiceIF;
import com.lps.edc.util.HttpHelper;
import com.lps.edc.util.ImageGetConfig;

@Service("imageService")
@Repository
public class ImageService implements ImageServiceIF {
	private ImageGetConfig config;
	
	@Override
	public byte[] getOneExamQuestion(String imageId, String questionId)
			throws Exception {
		StringBuffer url = new StringBuffer("method=");
		url.append(config.getImageGetMethod());
		url.append("&ID=");
		url.append(imageId);
		url.append("&QuestionID=");
		url.append(questionId);
		url.append("&fmt=");
		url.append(config.getFmt());
		HttpEntity data = new StringEntity(url.toString());
		return HttpHelper.post(config.getImageGetUrl(), data);
	}
	
	@Resource(name="imageGetConfig")
	public void setConfig(ImageGetConfig config) {
		this.config = config;
	}
}

package com.lps.edc.service.interfaces;

import java.util.List;

import com.lps.edc.dto.SimpleDto;

public interface InfExamSubjectServiceIF {
	public List<SimpleDto> getByBatch(String batchId) throws Exception;
	public void update(String examId, String state) throws Exception;
}

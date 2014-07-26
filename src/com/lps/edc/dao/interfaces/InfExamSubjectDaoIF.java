package com.lps.edc.dao.interfaces;

import java.util.List;

import com.lps.edc.dto.SimpleDto;

public interface InfExamSubjectDaoIF {
	public List<SimpleDto> getByBatch(String batchId) throws Exception;
	public void update(String examId, String state) throws Exception;
}

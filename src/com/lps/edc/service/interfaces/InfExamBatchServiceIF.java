package com.lps.edc.service.interfaces;

import java.util.List;

import com.lps.edc.dto.SimpleDto;

public interface InfExamBatchServiceIF {
	public List<SimpleDto> getBySchool(String schoolId) throws Exception;
}

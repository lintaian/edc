package com.lps.edc.dao.interfaces;

import java.util.List;

import com.lps.edc.dto.SimpleDto;

public interface InfExamBatchDaoIF {
	public List<SimpleDto> getBySchool(String schoolId) throws Exception;
}

package com.lps.edc.service.interfaces;

import java.util.List;

import com.lps.edc.dto.StandardDto;
import com.lps.edc.entity.InfExamStandard;

public interface InfExamStandardServiceIF {
	public void add(InfExamStandard examStandard) throws Exception;
	public void update(InfExamStandard standard) throws Exception;
	public void update(int id, double score) throws Exception;
	public void del(String[] ids) throws Exception;
	public List<StandardDto> query(String schoolId, String teacherId) throws Exception;
}

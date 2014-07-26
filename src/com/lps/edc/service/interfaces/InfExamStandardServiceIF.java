package com.lps.edc.service.interfaces;

import java.util.List;

import com.lps.edc.dto.SimpleDto;
import com.lps.edc.entity.InfExamStandard;

public interface InfExamStandardServiceIF {
	public void add(InfExamStandard examStandard) throws Exception;
	public void update(InfExamStandard standard) throws Exception;
	public void del(String[] ids) throws Exception;
	public List<List<SimpleDto>> query(String schoolId, String teacherId) throws Exception;
}

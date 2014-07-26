package com.lps.edc.service.interfaces;

import java.util.List;

import com.lps.edc.dto.SimpleDto;

public interface SysWordServiceIF {
	public List<SimpleDto> getGrades(String schoolId) throws Exception;
	public List<SimpleDto> getClasses(String gradeId) throws Exception;
	public List<SimpleDto> getSubjects() throws Exception;
	public List<SimpleDto> getSubjectTypes() throws Exception;
	public List<SimpleDto> getStandardTypes() throws Exception;
	public SimpleDto getSubjectTypeId(String subjectId) throws Exception;
}

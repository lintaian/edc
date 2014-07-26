package com.lps.edc.dao.interfaces;

import java.util.List;

import com.lps.edc.dto.SimpleDto;
import com.lps.edc.entity.SysWord;

public interface SysWordDaoIF {
	public List<SimpleDto> getGrades(String schoolId) throws Exception;
	public List<SimpleDto> getClasses(String gradeId) throws Exception;
	public List<SimpleDto> getSubjects() throws Exception;
	public List<SimpleDto> getSubjectTypes() throws Exception;
	public List<SimpleDto> getStandardTypes() throws Exception;
	public List<SimpleDto> getQuestionTypes() throws Exception;
	public SimpleDto getSubjectTypeId(String subjectId) throws Exception;
	public SysWord get(String wordId) throws Exception;
	public SimpleDto getSimpleDto(String wordId) throws Exception;
}

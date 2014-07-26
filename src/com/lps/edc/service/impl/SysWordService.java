package com.lps.edc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.lps.edc.dao.interfaces.SysWordDaoIF;
import com.lps.edc.dto.SimpleDto;
import com.lps.edc.service.interfaces.SysWordServiceIF;
@Service("wordService")
@Repository
public class SysWordService implements SysWordServiceIF {

	@Resource
	private SysWordDaoIF wordDao;

	@Override
	public List<SimpleDto> getGrades(String schoolId) throws Exception {
		return wordDao.getGrades(schoolId);
	}
	@Override
	public List<SimpleDto> getClasses(String gradeId) throws Exception {
		return wordDao.getClasses(gradeId);
	}
	@Override
	public List<SimpleDto> getSubjectTypes() throws Exception {
		return wordDao.getSubjectTypes();
	}
	@Override
	public List<SimpleDto> getSubjects() throws Exception {
		return wordDao.getSubjects();
	}
	@Override
	public List<SimpleDto> getStandardTypes() throws Exception {
		return wordDao.getStandardTypes();
	}
	@Override
	public SimpleDto getSubjectTypeId(String subjectId) throws Exception {
		return wordDao.getSubjectTypeId(subjectId);
	}
}

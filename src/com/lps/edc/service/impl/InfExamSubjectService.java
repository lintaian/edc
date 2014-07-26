package com.lps.edc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.lps.edc.dao.interfaces.InfExamSubjectDaoIF;
import com.lps.edc.dto.SimpleDto;
import com.lps.edc.service.interfaces.InfExamSubjectServiceIF;

@Service("subjectService")
@Repository
public class InfExamSubjectService implements InfExamSubjectServiceIF {

	@Resource
	private InfExamSubjectDaoIF subjectDao;
	
	@Override
	public List<SimpleDto> getByBatch(String batchId) throws Exception {
		return subjectDao.getByBatch(batchId);
	}
	@Override
	public void update(String examId, String state) throws Exception {
		subjectDao.update(examId, state);
	}
}

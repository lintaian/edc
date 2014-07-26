package com.lps.edc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.lps.edc.dao.interfaces.InfExamBatchDaoIF;
import com.lps.edc.dto.SimpleDto;
import com.lps.edc.service.interfaces.InfExamBatchServiceIF;

@Service("batchService")
@Repository
public class InfExamBatchService implements InfExamBatchServiceIF {

	@Resource
	private InfExamBatchDaoIF batchDao;
	
	@Override
	public List<SimpleDto> getBySchool(String schoolId) throws Exception {
		return batchDao.getBySchool(schoolId);
	}
}

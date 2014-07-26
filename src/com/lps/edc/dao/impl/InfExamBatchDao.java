package com.lps.edc.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lps.edc.dao.interfaces.InfExamBatchDaoIF;
import com.lps.edc.dto.SimpleDto;

@Repository
public class InfExamBatchDao extends BaseDao implements InfExamBatchDaoIF {
	@SuppressWarnings("unchecked")
	@Override
	public List<SimpleDto> getBySchool(String schoolId) throws Exception {
		String hql = "select new com.lps.edc.dto.SimpleDto(a.batchId, a.batchName) from InfExamBatch a where a.schoolId = ?";
		List<SimpleDto> batchs = (List<SimpleDto>)getHibernateTemplate().find(hql, schoolId);
		return batchs;
	}
}

package com.lps.edc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.lps.edc.dao.interfaces.InfExamSubjectDaoIF;
import com.lps.edc.dto.SimpleDto;

@Repository
public class InfExamSubjectDao extends BaseDao implements InfExamSubjectDaoIF {
	@SuppressWarnings("unchecked")
	@Override
	public List<SimpleDto> getByBatch(String batchId) throws Exception {
		String hql = "select new com.lps.edc.dto.SimpleDto(a.examId,a.examName,a.state) from InfExamSubject a where a.batchId = ?";
		List<SimpleDto> examSubjects = (List<SimpleDto>)getHibernateTemplate().find(hql, batchId);
		return examSubjects;
	}
	@Override
	public void update(String examId, String state) throws Exception {
		String hql = "update InfExamSubject a set a.state = ? where a.examId = ?";
		Query q = getSession(true).createQuery(hql);
		q.setString(0, state);
		q.setString(1, examId);
		q.executeUpdate();
	}
}

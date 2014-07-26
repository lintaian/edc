package com.lps.edc.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lps.edc.dao.interfaces.InfExamStandardDaoIF;
import com.lps.edc.entity.InfExamStandard;

@Repository
public class InfExamStandardDao extends BaseDao implements InfExamStandardDaoIF {

	@Override
	public void add(InfExamStandard examStandard) throws Exception {
		getHibernateTemplate().save(examStandard);
	}
	@Override
	public void update(InfExamStandard standard) throws Exception {
		getHibernateTemplate().update(standard);
	}
	@Override
	public void del(InfExamStandard standard) throws Exception {
		getHibernateTemplate().delete(standard);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<InfExamStandard> query(String schoolId, String teacherId)
			throws Exception {
		String hql = "from InfExamStandard a where a.schoolId=? and a.teacherId=?";
		List<InfExamStandard> examStandards = (List<InfExamStandard>)getHibernateTemplate().find(hql, schoolId, teacherId);
		return examStandards;
	}
}

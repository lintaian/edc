package com.lps.edc.dao.impl;

import java.util.List;

import org.hibernate.Query;
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
	@Override
	public void update(int id, double score) throws Exception {
		String hql = "update InfExamStandard a set a.score = ? where a.id = ?";
		Query q = getSession(true).createQuery(hql);
		q.setDouble(0, score);
		q.setInteger(1, id);
		q.executeUpdate();
	}
}

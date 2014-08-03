package com.lps.edc.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.lps.edc.dao.interfaces.InfExamQuestionResultZgDaoIF;
import com.lps.edc.entity.InfExamQuestionResultZg;

@Repository
public class InfExamQuestionResultZgDao extends BaseDao implements InfExamQuestionResultZgDaoIF {
	@Override
	public void save(InfExamQuestionResultZg zg) throws Exception {
		getHibernateTemplate().save(zg);
	}
	@Override
	public void del(InfExamQuestionResultZg zg) throws Exception {
		String hql = "delete from InfExamQuestionResultZg a where a.examId=? and a.questionId=? and a.imageId=?";
		Query q = getSession(true).createQuery(hql);
		q.setString(0, zg.getExamId());
		q.setString(1, zg.getQuestionId());
		q.setString(2, zg.getImageId());
		q.executeUpdate();
	}
}

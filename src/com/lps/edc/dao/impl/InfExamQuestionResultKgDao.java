package com.lps.edc.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.lps.edc.dao.interfaces.InfExamQuestionResultKgDaoIF;
import com.lps.edc.entity.InfExamQuestionResultKg;

@Repository
public class InfExamQuestionResultKgDao extends BaseDao implements InfExamQuestionResultKgDaoIF {
	@Override
	public void save(InfExamQuestionResultKg kg) throws Exception {
		getHibernateTemplate().save(kg);
	}
	@Override
	public void del(InfExamQuestionResultKg kg) throws Exception {
		String hql = "delete from InfExamQuestionResultKg a where a.examId=? and a.questionId=? and a.imageId=?";
		Query q = getSession(true).createQuery(hql);
		q.setString(0, kg.getExamId());
		q.setString(1, kg.getQuestionId());
		q.setString(2, kg.getImageId());
		q.executeUpdate();
	}
}

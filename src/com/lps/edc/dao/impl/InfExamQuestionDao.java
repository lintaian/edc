package com.lps.edc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.lps.edc.dao.interfaces.InfExamQuestionDaoIF;
import com.lps.edc.entity.InfExamQuestion;

@Repository
public class InfExamQuestionDao extends BaseDao implements InfExamQuestionDaoIF {
	@SuppressWarnings("unchecked")
	@Override
	public List<InfExamQuestion> getByExam(String examId) throws Exception {
		String hql = "from InfExamQuestion a where a.examid = ? order by a.questionOrder";
		List<InfExamQuestion> examQuestions = (List<InfExamQuestion>)getHibernateTemplate().find(hql, examId);
		return examQuestions;
	}
	@Override
	public void update(String questionId, String questionName,
			String questionOrder) throws Exception {
		String hql = "update InfExamQuestion a set a.questionName=?, a.questionOrder=? where a.questionId=?";
		Query q = getSession().createQuery(hql);
		q.setString(0, questionName);
		q.setString(1, questionOrder);
		q.setString(2, questionId);
		q.executeUpdate();
	}
	@SuppressWarnings("unchecked")
	@Override
	public InfExamQuestion getByName(String name) throws Exception {
		String hql = "from InfExamQuestion a where a.questionName = ?";
		List<InfExamQuestion> examQuestions = (List<InfExamQuestion>)getHibernateTemplate().find(hql, name);
		if (examQuestions.size() > 0) {
			return examQuestions.get(0);
		}
		return null;
	}
}

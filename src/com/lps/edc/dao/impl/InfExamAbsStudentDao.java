package com.lps.edc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.lps.edc.dao.interfaces.InfExamAbsStudentDaoIf;
import com.lps.edc.dto.AbsStudentDto;
import com.lps.edc.dto.SimpleDto;
import com.lps.edc.entity.InfExamAbsStudent;

@Repository
public class InfExamAbsStudentDao extends BaseDao implements InfExamAbsStudentDaoIf {

	@Override
	public void add(InfExamAbsStudent absStudent) throws Exception {
		getHibernateTemplate().save(absStudent);
	}
	@Override
	public void del(int id) throws Exception {
		String hql = "del InfExamAbsStudent where id = ?";
		Query q = getSession().createQuery(hql);
		q.setInteger(0, id);
		q.executeUpdate();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<AbsStudentDto> query(String examId) throws Exception {
		String hql = "select a.id as id,a.examno as examNo,b.studentName as studentName from InfExamAbsStudent a,SysStudent b where a.examno=b.examNo and a.examid = ?";
		List<AbsStudentDto> dtos = (List<AbsStudentDto>)getHibernateTemplate().find(hql, examId);
		return dtos;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<SimpleDto> queryAllExceptAbs(String gradeId, String examId) throws Exception {
		String hql = "select new com.lps.edc.dto.SimpleDto(b.ExamNo,b.StudentName) from SysStudent b "
				+ "where b.GradeID = ? and b.ExamNo "
				+ "not in(select a.examno from InfExamAbsStudent a where a.examid=?)";
		List<SimpleDto> dtos = (List<SimpleDto>)getHibernateTemplate().find(hql, gradeId, examId);
		return dtos;
	}
}

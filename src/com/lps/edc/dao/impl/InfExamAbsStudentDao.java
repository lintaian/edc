package com.lps.edc.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.lps.edc.dao.interfaces.InfExamAbsStudentDaoIf;
import com.lps.edc.dto.AbsStudentDto;
import com.lps.edc.dto.SimpleDto;
import com.lps.edc.entity.InfExamAbsStudent;
import com.lps.edc.entity.SysStudent;

@Repository
public class InfExamAbsStudentDao extends BaseDao implements InfExamAbsStudentDaoIf {

	@Override
	public void add(InfExamAbsStudent absStudent) throws Exception {
		getHibernateTemplate().save(absStudent);
	}
	@Override
	public void del(int id) throws Exception {
		String hql = "delete from InfExamAbsStudent where id = ?";
		Query q = getSession(true).createQuery(hql);
		q.setInteger(0, id);
		q.executeUpdate();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<AbsStudentDto> query(String examId) throws Exception {
		String hql = "select new com.lps.edc.dto.AbsStudentDto(a.id, a.examno, b.studentName) from InfExamAbsStudent a," +
				"SysStudent b where a.examno=b.examNo and a.examid = ? order by a.id";
		List<AbsStudentDto> dtos = (List<AbsStudentDto>)getHibernateTemplate().find(hql, examId);
		return dtos;
	}
	@SuppressWarnings("unchecked")
	@Override
	public AbsStudentDto query(String examId, String examNo) throws Exception {
		String hql = "select new com.lps.edc.dto.AbsStudentDto(a.id, a.examno, b.studentName) from InfExamAbsStudent a," +
				"SysStudent b where a.examno=b.examNo and a.examno = ? and a.examid = ?";
		List<AbsStudentDto> dtos = (List<AbsStudentDto>)getHibernateTemplate().find(hql, examNo, examId);
		if (dtos.size() > 0) {
			return dtos.get(0);
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<SimpleDto> queryAllExceptAbs(String gradeId, String examId) throws Exception {
		String hql = "select new com.lps.edc.dto.SimpleDto(b.examNo,b.studentName) from SysStudent b "
				+ "where b.gradeId = ? and b.examNo "
				+ "not in(select a.examno from InfExamAbsStudent a where a.examid=?)";
		List<SimpleDto> dtos = (List<SimpleDto>)getHibernateTemplate().find(hql, gradeId, examId);
		return dtos;
	}
	@SuppressWarnings("unchecked")
	@Override
	public SysStudent queryExceptAbs(String gradeId, String examNo) throws Exception {
		String hql = "from SysStudent b where b.gradeId = ? and b.examNo = ?";
		List<SysStudent> dtos = (List<SysStudent>)getHibernateTemplate().find(hql, gradeId, examNo);
		if (dtos.size() > 0) {
			return dtos.get(0);
		}
		return null;
	}
}

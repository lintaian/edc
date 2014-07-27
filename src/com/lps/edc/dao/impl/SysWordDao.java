package com.lps.edc.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lps.edc.dao.interfaces.SysWordDaoIF;
import com.lps.edc.dto.SimpleDto;
import com.lps.edc.entity.SysWord;

@Repository
public class SysWordDao extends BaseDao implements SysWordDaoIF {
	@SuppressWarnings("unchecked")
	@Override
	public List<SimpleDto> getClasses(String gradeId) throws Exception {
		String hql = "select new com.lps.edc.dto.SimpleDto(a.wordId, a.wordInfo, c.extent, c.wordInfo) from SysWord a,"
				+ "InfSchoolClass b, SysWord c where a.wordId=b.classId and b.gradeId = ? and b.wlTypeId = c.wordId" +
				" order by a.id";
		List<SimpleDto> sysWords = (List<SimpleDto>)getHibernateTemplate().find(hql, gradeId);
		return sysWords;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<SimpleDto> getGrades(String schoolId) throws Exception {
		String hql = "select distinct new com.lps.edc.dto.SimpleDto(a.wordId,a.wordInfo) from SysWord a,"
				+ "InfSchoolClass b where a.wordId=b.gradeId and b.schoolId = ?";
		List<SimpleDto> grades = (List<SimpleDto>)getHibernateTemplate().find(hql, schoolId);
		return grades;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<SimpleDto> getSubjects() throws Exception {
		String hql = "select new com.lps.edc.dto.SimpleDto(a.wordId,a.wordInfo) from SysWord a where a.wordType='科目'";
		List<SimpleDto> subjects = (List<SimpleDto>)getHibernateTemplate().find(hql);
		return subjects;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<SimpleDto> getStandardTypes() throws Exception {
		String hql = "select new com.lps.edc.dto.SimpleDto(a.wordId,a.wordInfo) from SysWord a where a.wordType='达标类型'";
		List<SimpleDto> standardTypes = (List<SimpleDto>)getHibernateTemplate().find(hql);
		return standardTypes;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<SimpleDto> getSubjectTypes() throws Exception {
		String hql = "select new com.lps.edc.dto.SimpleDto(a.wordId,a.wordInfo) from SysWord a where a.wordType='文理'";
		List<SimpleDto> standardTypes = (List<SimpleDto>)getHibernateTemplate().find(hql);
		return standardTypes;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<SimpleDto> getQuestionTypes() throws Exception {
		String hql = "select new com.lps.edc.dto.SimpleDto(a.wordId,a.wordInfo) from SysWord a where a.wordType='题型'";
		List<SimpleDto> data = (List<SimpleDto>)getHibernateTemplate().find(hql);
		return data;
	}
	@SuppressWarnings("unchecked")
	@Override
	public SimpleDto getSubjectTypeId(String subjectId) throws Exception {
		String hql = "select new com.lps.edc.dto.SimpleDto(b.wordId,b.wordInfo) from SysWord a,SysWord b where a.wordId = ? "
				+ "and a.extent = b.wordType";
		List<SimpleDto> dtos = (List<SimpleDto>)getHibernateTemplate().find(hql, subjectId);
		if (dtos.size() > 0) {
			return dtos.get(0);
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public SysWord get(String wordId) throws Exception {
		String hql = "from SysWord a where a.wordId = ? ";
		List<SysWord> dtos = (List<SysWord>)getHibernateTemplate().find(hql, wordId);
		if (dtos.size() > 0) {
			return dtos.get(0);
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public SimpleDto getSimpleDto(String wordId) throws Exception {
		String hql = "select new com.lps.edc.dto.SimpleDto(a.wordId,a.wordInfo) from SysWord a where a.wordId = ? ";
		List<SimpleDto> dtos = (List<SimpleDto>)getHibernateTemplate().find(hql, wordId);
		if (dtos.size() > 0) {
			return dtos.get(0);
		}
		return null;
	}
}

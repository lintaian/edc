package com.lps.edc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.lps.edc.dao.interfaces.InfExamAbsStudentDaoIf;
import com.lps.edc.dto.AbsStudentDto;
import com.lps.edc.dto.SimpleDto;
import com.lps.edc.entity.InfExamAbsStudent;
import com.lps.edc.service.interfaces.InfExamAbsStudentServiceIF;

@Service("absStudentService")
@Repository
public class InfExamAbsStudentService implements InfExamAbsStudentServiceIF {

	@Resource
	private InfExamAbsStudentDaoIf absStudentDao;
	
	@Override
	public void add(InfExamAbsStudent absStudent) throws Exception {
		absStudentDao.add(absStudent);
	}
	@Override
	public void add(List<InfExamAbsStudent> absStudents) throws Exception {
		for (InfExamAbsStudent absStudent : absStudents) {
			absStudentDao.add(absStudent);
		}
	}
	@Override
	public void del(int id) throws Exception {
		absStudentDao.del(id);
	}
	@Override
	public List<AbsStudentDto> query(String examId) throws Exception {
		return absStudentDao.query(examId);
	}
	@Override
	public List<SimpleDto> queryAllExceptAbs(String gradeId, String examId)
			throws Exception {
		return absStudentDao.queryAllExceptAbs(gradeId, examId);
	}
}

package com.lps.edc.dao.interfaces;

import java.util.List;

import com.lps.edc.dto.AbsStudentDto;
import com.lps.edc.dto.SimpleDto;
import com.lps.edc.entity.InfExamAbsStudent;
import com.lps.edc.entity.SysStudent;

public interface InfExamAbsStudentDaoIf {
	public void add(InfExamAbsStudent absStudent) throws Exception;
	public void del(int id) throws Exception;
	public List<AbsStudentDto> query(String examId) throws Exception;
	public AbsStudentDto query(String examId, String examNo) throws Exception;
	public List<SimpleDto> queryAllExceptAbs(String gradeId, String examId) throws Exception;
	public SysStudent queryExceptAbs(String gradeId, String examNo) throws Exception;
}

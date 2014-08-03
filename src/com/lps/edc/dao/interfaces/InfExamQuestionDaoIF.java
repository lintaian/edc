package com.lps.edc.dao.interfaces;

import java.util.List;

import com.lps.edc.entity.InfExamQuestion;
import com.lps.edc.entity.SysWord;

public interface InfExamQuestionDaoIF {
	public List<InfExamQuestion> getByExam(String examId) throws Exception;
	public void update(String questionId, String questionName, String questionOrder) throws Exception;
	public InfExamQuestion getByName(String name) throws Exception;
	public InfExamQuestion get(String examId, String name) throws Exception;
	public SysWord getQuestionType(String id) throws Exception;
}

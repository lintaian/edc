package com.lps.edc.service.impl;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.lps.edc.dao.interfaces.CountDAOIF;
import com.lps.edc.dto.TableDto;
import com.lps.edc.service.interfaces.CountServiceIF;
@Service("countService")
@Repository
public class CountService implements CountServiceIF {
	@Resource
	private CountDAOIF countDao;

	@Override
	public TableDto getQuestionScore(String gradeId, String examId,
			JSONArray classList, JSONArray questionList, String studentName,
			String examNo) throws Exception {
		return countDao.getQuestionScore(gradeId, examId, classList, questionList, studentName, examNo);
	}
	@Override
	public TableDto getClassAgv(String gradeId, String examId,
			JSONArray classList, JSONArray questionList, String studentName,
			String examNo) throws Exception {
		return countDao.getClassAgv(gradeId, examId, classList, questionList, studentName, examNo);
	}
	@Override
	public TableDto getAnswerNum(String gradeId, String examId,
			JSONArray classList, JSONArray questionList, String studentName,
			String examNo) throws Exception {
		return countDao.getAnswerNum(gradeId, examId, classList, questionList, studentName, examNo);
	}
	@Override
	public TableDto getOriginalAnswer(String gradeId, String examId,
			JSONArray classList, JSONArray questionList, String studentName,
			String examNo) throws Exception {
		return countDao.getOriginalAnswer(gradeId, examId, classList, questionList, studentName, examNo);
	}
	@Override
	public TableDto getStudentKnowledge(String examid) throws Exception {
		return countDao.getStudentKnowledge(examid);
	}
	@Override
	public TableDto getClassKnowledge(String examid) throws Exception {
		return countDao.getClassKnowledge(examid);
	}
	@Override
	public TableDto getStudentPower(String examid) throws Exception {
		return countDao.getStudentPower(examid);
	}
	@Override
	public TableDto getClassPower(String examid) throws Exception {
		return countDao.getClassPower(examid);
	}
	@Override
	public void countScore(String examid, int type) throws Exception {
		countDao.countScore(examid, type);
	}
}

package com.lps.edc.dao.interfaces;

import net.sf.json.JSONArray;
import com.lps.edc.dto.TableDto;

public interface CountDAOIF {
	public TableDto getQuestionScore(String gradeId, String examId, 
			JSONArray classList, JSONArray questionList, 
			String studentName, String examNo) throws Exception;
	public TableDto getClassAgv(String gradeId, String examId, 
			JSONArray classList, JSONArray questionList, 
			String studentName, String examNo) throws Exception;
	public TableDto getAnswerNum(String gradeId, String examId, 
			JSONArray classList, JSONArray questionList, 
			String studentName, String examNo) throws Exception;
	public TableDto getOriginalAnswer(String gradeId, String examId, 
			JSONArray classList, JSONArray questionList, 
			String studentName, String examNo) throws Exception;
	public TableDto getStudentKnowledge(String examid) throws Exception;
	public TableDto getClassKnowledge(String examid) throws Exception;
	public TableDto getStudentPower(String examid) throws Exception;
	public TableDto getClassPower(String examid) throws Exception;
	public void countScore(String examid, int type) throws Exception;
}

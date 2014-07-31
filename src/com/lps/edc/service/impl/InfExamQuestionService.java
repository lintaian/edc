package com.lps.edc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.lps.edc.dao.interfaces.InfExamQuestionDaoIF;
import com.lps.edc.dao.interfaces.SysWordDaoIF;
import com.lps.edc.dto.QuestionDto;
import com.lps.edc.dto.SimpleDto;
import com.lps.edc.entity.InfExamQuestion;
import com.lps.edc.service.interfaces.InfExamQuestionServiceIF;

@Service("questionService")
@Repository
public class InfExamQuestionService implements InfExamQuestionServiceIF {
	
	@Resource
	private InfExamQuestionDaoIF questionDao;
	@Resource
	private SysWordDaoIF wordDao;
	
	@Override
	public List<InfExamQuestion> getByExam(String examId) throws Exception {
		return questionDao.getByExam(examId);
	}

	@Override
	public void update(String questionId, String questionName,
			String questionOrder) throws Exception {
		questionDao.update(questionId, questionName, questionOrder);
	}
	@Override
	public InfExamQuestion getByName(String name) throws Exception {
		return questionDao.getByName(name);
	}
	@Override
	public List<QuestionDto> getQuestionDto(String examId) throws Exception {
		List<InfExamQuestion> questions = questionDao.getByExam(examId);
		return parse(questions);
	}
	private List<QuestionDto> parse(List<InfExamQuestion> questions) throws Exception {
		List<QuestionDto> dtos = new ArrayList<QuestionDto>();
		List<SimpleDto> types = wordDao.getQuestionTypes();
		JSONObject type = new JSONObject();
		for (SimpleDto simpleDto : types) {
			type.put(simpleDto.getId().toUpperCase(), simpleDto.getName());
		}
		for (InfExamQuestion q : questions) {
			boolean flag = true;
			QuestionDto qd = parse2(q, type);
			for (QuestionDto dto : dtos) {
				if (dto.getId().equals(q.getParentid())) {
					dto.getChild().add(qd);
					flag = false;
					break;
				}
			}
			if (flag) {
				dtos.add(qd);
			}
		}
		return dtos;
	}
	private QuestionDto parse2(InfExamQuestion q, JSONObject type) {
		QuestionDto dto = new QuestionDto();
		dto.setId(q.getQuestionId());
		dto.setName(q.getQuestionName());
		dto.setOrder(q.getQuestionOrder());
		StringBuffer sb = new StringBuffer();
		sb.append("名称:");
		sb.append(q.getQuestionName());
		sb.append(",序号:");
		sb.append(q.getQuestionOrder());
		sb.append(",分数:");
		sb.append(q.getQuestionNum());
		sb.append(",类型:");
		String t = type.has(q.getQuestionTypeId().toUpperCase()) ? type.getString(q.getQuestionTypeId().toUpperCase()) : "";
		sb.append(t);
		sb.append(",答案:");
		sb.append(q.getQuestionResult());
		sb.append(",Id:");
		sb.append(q.getQuestionId());
		dto.setShow(sb.toString());
		return dto;
	}
}

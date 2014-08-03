package com.lps.edc.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lps.edc.dto.QuestionDto;
import com.lps.edc.entity.InfExamQuestion;
import com.lps.edc.entity.InfExamQuestionResultKg;
import com.lps.edc.entity.InfExamQuestionResultZg;
import com.lps.edc.entity.SysWord;
import com.lps.edc.service.interfaces.InfExamQuestionResultKgServiceIF;
import com.lps.edc.service.interfaces.InfExamQuestionResultZgServiceIF;
import com.lps.edc.service.interfaces.InfExamQuestionServiceIF;

@Controller
@RequestMapping("question")
public class QuestionController {
	@Resource
	private InfExamQuestionServiceIF questionService;
	@Resource
	private InfExamQuestionResultKgServiceIF questionResutlKgService;
	@Resource
	private InfExamQuestionResultZgServiceIF questionResutlZgService;
	
	@ResponseBody
	@RequestMapping(value="/exam/{id}", method=RequestMethod.GET)
	public JSONArray query(@PathVariable String id) throws Exception {
		List<QuestionDto> data = questionService.getQuestionDto(id);
		return JSONArray.fromObject(data);
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public JSONObject update(@PathVariable String id, @RequestBody JSONObject reqBody) throws Exception {
		JSONObject json = new JSONObject();
		questionService.update(id, reqBody.getString("name"), reqBody.getString("order"));
		json.put("status", true);
		return json;
	}
	
	@SuppressWarnings("deprecation")
	@ResponseBody
	@RequestMapping(value="/answer", method=RequestMethod.PUT)
	public JSONObject updateZg(@RequestBody JSONObject reqBody) throws Exception {
		JSONObject json = new JSONObject();
		json.put("status", true);
		String examId = reqBody.getString("examId");
		String questionName = reqBody.getString("questionName");
		String result = reqBody.getString("result");
		String imageId = reqBody.getString("imageId");
		InfExamQuestion question = questionService.get(examId, questionName);
		if (question != null) {
			String questionId = question.getQuestionId();
			SysWord type = questionService.getQuestionType(questionId);
			if (type != null) {
				if ("主观题".equals(type.getWordInfo())) {
					InfExamQuestionResultZg zg = new InfExamQuestionResultZg();
					zg.setExamId(examId);
					zg.setImageId(imageId);
					zg.setQuestionId(questionId);
					zg.setResult(result);
					zg.setInsertTime(new Date());
					questionResutlZgService.update(zg);
				} else {
					InfExamQuestionResultKg kg = new InfExamQuestionResultKg();
					kg.setExamId(examId);
					kg.setImageId(imageId);
					kg.setQuestionId(questionId);
					kg.setResult(result);
					kg.setInsertTime((new Date()).toLocaleString());
					questionResutlKgService.update(kg);
				}
			} else {
				json.put("status", false);
				json.put("msg", "题目类型错误");
			}
		} else {
			json.put("status", false);
			json.put("msg", "题目名字错误");
		}
		return json;
	}
}

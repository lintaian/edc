package com.lps.edc.controller;

import javax.annotation.Resource;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lps.edc.dto.TableDto;
import com.lps.edc.service.interfaces.CountServiceIF;

@Controller
@RequestMapping("count")
public class CountController {
	@Resource
	private CountServiceIF countService;
	
	@ResponseBody
	@RequestMapping(value="/countScore", method=RequestMethod.POST)
	public JSONObject countScore(@RequestBody JSONObject reqBody) throws Exception {
		countService.countScore(reqBody.getString("examId"), reqBody.getInt("type"));
		return null;
	}
	@ResponseBody
	@RequestMapping(value="/answerNum", method=RequestMethod.POST)
	public JSONObject getAnswerNum(@RequestBody JSONObject reqBody) throws Exception {
		TableDto data = countService.getAnswerNum(reqBody.getString("gradeId"), reqBody.getString("examId"), 
				reqBody.getJSONArray("classList"), reqBody.getJSONArray("questionList"), 
				reqBody.getString("studentName"), reqBody.getString("examNo"));
		return JSONObject.fromObject(data);
	}
	@ResponseBody
	@RequestMapping(value="/classAvg", method=RequestMethod.POST)
	public JSONObject getClassAgv(@RequestBody JSONObject reqBody) throws Exception {
		TableDto data = countService.getClassAgv(reqBody.getString("gradeId"), reqBody.getString("examId"), 
				reqBody.getJSONArray("classList"), reqBody.getJSONArray("questionList"), 
				reqBody.getString("studentName"), reqBody.getString("examNo"));
		return JSONObject.fromObject(data);
	}
	@ResponseBody
	@RequestMapping(value="/originalAnswer", method=RequestMethod.POST)
	public JSONObject getOriginalAnswer(@RequestBody JSONObject reqBody) throws Exception {
		TableDto data = countService.getOriginalAnswer(reqBody.getString("gradeId"), reqBody.getString("examId"), 
				reqBody.getJSONArray("classList"), reqBody.getJSONArray("questionList"), 
				reqBody.getString("studentName"), reqBody.getString("examNo"));
		return JSONObject.fromObject(data);
	}
	@ResponseBody
	@RequestMapping(value="/questionScore", method=RequestMethod.POST)
	public JSONObject getQuestionScore(@RequestBody JSONObject reqBody) throws Exception {
		TableDto data = countService.getQuestionScore(reqBody.getString("gradeId"), reqBody.getString("examId"), 
				reqBody.getJSONArray("classList"), reqBody.getJSONArray("questionList"), 
				reqBody.getString("studentName"), reqBody.getString("examNo"));
		return JSONObject.fromObject(data);
	}
	@ResponseBody
	@RequestMapping(value="/classKnowledge/{id}", method=RequestMethod.GET)
	public JSONObject getClassKnowledge(@PathVariable String id) throws Exception {
		TableDto data = countService.getClassKnowledge(id);
		return JSONObject.fromObject(data);
	}
	@ResponseBody
	@RequestMapping(value="/classPower/{id}", method=RequestMethod.GET)
	public JSONObject getClassPower(@PathVariable String id) throws Exception {
		TableDto data = countService.getClassPower(id);
		return JSONObject.fromObject(data);
	}
	@ResponseBody
	@RequestMapping(value="/studentKnowledge/{id}", method=RequestMethod.GET)
	public JSONObject getStudentKnowledge(@PathVariable String id) throws Exception {
		TableDto data = countService.getStudentKnowledge(id);
		return JSONObject.fromObject(data);
	}
	@ResponseBody
	@RequestMapping(value="/studentPower/{id}", method=RequestMethod.GET)
	public JSONObject getStudentPower(@PathVariable String id) throws Exception {
		TableDto data = countService.getStudentPower(id);
		return JSONObject.fromObject(data);
	}
}

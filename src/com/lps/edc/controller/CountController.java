package com.lps.edc.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lps.edc.dto.TableDto;
import com.lps.edc.service.interfaces.CountServiceIF;
import com.lps.edc.util.CSV;
import com.lps.edc.util.CSV.ReportType;
import com.lps.edc.util.Helper;

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
	public JSONObject getAnswerNum(@RequestBody JSONObject reqBody, HttpServletRequest req) throws Exception {
		TableDto data = countService.getAnswerNum(reqBody.getString("gradeId"), reqBody.getString("examId"), 
				reqBody.getJSONArray("classList"), reqBody.getJSONArray("questionList"), 
				reqBody.getString("studentName"), reqBody.getString("examNo"));
		CSV.create(data, req, ReportType.ANSWERNUMBER);
		return JSONObject.fromObject(data);
	}
	@ResponseBody
	@RequestMapping(value="/classAvg", method=RequestMethod.POST)
	public JSONObject getClassAgv(@RequestBody JSONObject reqBody, HttpServletRequest req) throws Exception {
		TableDto data = countService.getClassAgv(reqBody.getString("gradeId"), reqBody.getString("examId"), 
				reqBody.getJSONArray("classList"), reqBody.getJSONArray("questionList"), 
				reqBody.getString("studentName"), reqBody.getString("examNo"));
		CSV.create(data, req, ReportType.CLASSAVG);
		JSONObject obj = JSONObject.fromObject(data);
		return obj;
	}
	@ResponseBody
	@RequestMapping(value="/originalAnswer", method=RequestMethod.POST)
	public JSONObject getOriginalAnswer(@RequestBody JSONObject reqBody, HttpServletRequest req) throws Exception {
		TableDto data = countService.getOriginalAnswer(reqBody.getString("gradeId"), reqBody.getString("examId"), 
				reqBody.getJSONArray("classList"), reqBody.getJSONArray("questionList"), 
				reqBody.getString("studentName"), reqBody.getString("examNo"));
		CSV.create(data, req, ReportType.ORIGIALANSWER);
		data.setData(Helper.converToObj(data.getData()));
		JSONObject obj = JSONObject.fromObject(data);
		return obj;
	}
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/questionScore", method=RequestMethod.POST)
	public JSONObject getQuestionScore(@RequestBody JSONObject reqBody, HttpServletRequest req) throws Exception {
		TableDto data = countService.getQuestionScore(reqBody.getString("gradeId"), reqBody.getString("examId"), 
				reqBody.getJSONArray("classList"), reqBody.getJSONArray("questionList"), 
				reqBody.getString("studentName"), reqBody.getString("examNo"));
		List<Object> title = data.getTitle();
		title.set(0, "班级");
		title.set(1, "班级ID");
		title.set(2, "学生名字");
		title.set(3, "学生ID");
		title.set(4, "考号");
		title.add(5, "总分");
		data.setTitle(title);
		List<Object> d = data.getData();
		for (Object object : d) {
			List<Object> d2 = (List<Object>) object;
			double score = 0 ;
			double temp = 0;
			for (int i = 5; i < d2.size(); i++) {
				temp = "".equals(d2.get(i)) ? 0 : Double.parseDouble(d2.get(i).toString());
				score += temp;
			}
			d2.add(5, score);
		}
		data.setData(d);
		CSV.create(data, req, ReportType.QUESTIONSCORE);
		JSONObject obj = JSONObject.fromObject(data);
		return obj;
	}
	@ResponseBody
	@RequestMapping(value="/classKnowledge/{id}", method=RequestMethod.GET)
	public JSONObject getClassKnowledge(@PathVariable String id, HttpServletRequest req) throws Exception {
		TableDto data = countService.getClassKnowledge(id);
		CSV.create(data, req, ReportType.CLASSKNOWLEDGE);
		return JSONObject.fromObject(data);
	}
	@ResponseBody
	@RequestMapping(value="/classPower/{id}", method=RequestMethod.GET)
	public JSONObject getClassPower(@PathVariable String id, HttpServletRequest req) throws Exception {
		TableDto data = countService.getClassPower(id);
		CSV.create(data, req, ReportType.CLASSPOWER);
		return JSONObject.fromObject(data);
	}
	@ResponseBody
	@RequestMapping(value="/studentKnowledge/{id}", method=RequestMethod.GET)
	public JSONObject getStudentKnowledge(@PathVariable String id, HttpServletRequest req) throws Exception {
		TableDto data = countService.getStudentKnowledge(id);
		CSV.create(data, req, ReportType.STUDENTKNOWLEDGE);
		return JSONObject.fromObject(data);
	}
	@ResponseBody
	@RequestMapping(value="/studentPower/{id}", method=RequestMethod.GET)
	public JSONObject getStudentPower(@PathVariable String id, HttpServletRequest req) throws Exception {
		TableDto data = countService.getStudentPower(id);
		CSV.create(data, req, ReportType.STUDENTPOWER);
		return JSONObject.fromObject(data);
	}
	@RequestMapping(value="/download/{type}/{filename}")
	public void download(HttpServletRequest req, HttpServletResponse resp, @PathVariable ReportType type, @PathVariable String filename) {
		CSV.download(req, resp, type, filename + ".csv");
	}
}

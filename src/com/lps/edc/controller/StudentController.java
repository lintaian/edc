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

import com.lps.edc.dto.AbsStudentDto;
import com.lps.edc.dto.SimpleDto;
import com.lps.edc.entity.InfExamAbsStudent;
import com.lps.edc.service.interfaces.InfExamAbsStudentServiceIF;

@Controller
@RequestMapping("student")
public class StudentController {
	@Resource
	private InfExamAbsStudentServiceIF absStudentService;
	
	@ResponseBody
	@RequestMapping(value="/exam/{id}", method=RequestMethod.GET)
	public JSONArray query(@PathVariable String id) throws Exception {
		List<AbsStudentDto> data = absStudentService.query(id);
		return JSONArray.fromObject(data);
	}
	
	@ResponseBody
	@RequestMapping(value="", method=RequestMethod.POST)
	public JSONObject add(@RequestBody JSONObject reqBody) throws Exception {
		JSONObject json = new JSONObject();
		InfExamAbsStudent as = new InfExamAbsStudent();
		as.setExamid(reqBody.getString("examId"));
		as.setExamno(reqBody.getString("examNo"));
		as.setInserttime(new Date());
		json.put("status", true);
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public JSONObject del(@PathVariable int id) throws Exception {
		JSONObject json = new JSONObject();
		absStudentService.del(id);
		json.put("status", true);
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value="/queryAllExceptAbs/{gId}/{eId}", method=RequestMethod.GET)
	public JSONArray queryAllExceptAbs(@PathVariable String gId, @PathVariable String eId) throws Exception {
		List<SimpleDto> data = absStudentService.queryAllExceptAbs(gId, eId);
		return JSONArray.fromObject(data);
	}
	
}

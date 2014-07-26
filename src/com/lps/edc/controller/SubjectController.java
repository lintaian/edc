package com.lps.edc.controller;

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

import com.lps.edc.dto.SimpleDto;
import com.lps.edc.service.interfaces.InfExamSubjectServiceIF;

@Controller
@RequestMapping("subject")
public class SubjectController {
	@Resource
	private InfExamSubjectServiceIF subjectService;
	
	@ResponseBody
	@RequestMapping(value="/batch/{id}", method=RequestMethod.GET)
	public JSONArray query(@PathVariable String id) throws Exception {
		List<SimpleDto> data = subjectService.getByBatch(id);
		return JSONArray.fromObject(data);
	}
	
	@ResponseBody
	@RequestMapping(value="", method=RequestMethod.PUT)
	public JSONObject update(@RequestBody JSONObject reqBody) throws Exception {
		subjectService.update(reqBody.getString("examId"), reqBody.getString("state"));
		return null;
	}
}

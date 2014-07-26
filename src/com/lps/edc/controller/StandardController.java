package com.lps.edc.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lps.edc.dto.SimpleDto;
import com.lps.edc.entity.InfExamStandard;
import com.lps.edc.entity.SysTeacher;
import com.lps.edc.entity.SysUser;
import com.lps.edc.service.interfaces.InfExamStandardServiceIF;
import com.lps.edc.service.interfaces.SysWordServiceIF;

@Controller
@RequestMapping("standard")
public class StandardController {
	@Resource
	private InfExamStandardServiceIF standardService;
	@Resource
	private SysWordServiceIF wordService;
	
	@ResponseBody
	@RequestMapping(value="", method=RequestMethod.POST)
	public JSONObject add(@RequestBody JSONObject reqBody, HttpServletRequest req) throws Exception {
		String subjectId = reqBody.getString("subjectId");
		String subjectTypeId = reqBody.getString("subjectTypeId");
		String tId = "";
		if ("admin".equals(req.getSession().getAttribute("role").toString())) {
			SysUser user = (SysUser) req.getSession().getAttribute("user");
			tId = user.getUserId();
		} else {
			SysTeacher teacher = (SysTeacher) req.getSession().getAttribute("user");
			tId = teacher.getTeacherid();
		}
		List<SimpleDto> temp = wordService.getStandardTypes();
		for (SimpleDto simpleDto : temp) {
			InfExamStandard standard = new InfExamStandard();
			standard.setSchoolId(reqBody.getString("schoolId"));
			standard.setScore(0);
			standard.setSubjectId(subjectId);
			standard.setSubjectTypeId(subjectTypeId);
			standard.setTeacherId(tId);
			standard.setUpdateTime(new Date());
			standard.setStandardTypeId(simpleDto.getId());
			standardService.add(standard);
		}
		return null;
	}
	@ResponseBody
	@RequestMapping(value="/{ids}", method=RequestMethod.DELETE)
	public JSONObject del(@PathVariable String ids) throws Exception {
		String[] ids2 = ids.split(",");
		standardService.del(ids2);
		return null;
	}
	@ResponseBody
	@RequestMapping(value="", method=RequestMethod.PUT)
	public JSONObject update(@RequestBody InfExamStandard standard) throws Exception {
		standardService.update(standard);
		return null;
	}
	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public JSONArray query(@PathVariable String id, HttpServletRequest req) throws Exception {
		String tId = "";
		if ("admin".equals(req.getSession().getAttribute("role").toString())) {
			SysUser user = (SysUser) req.getSession().getAttribute("user");
			tId = user.getUserId();
		} else {
			SysTeacher teacher = (SysTeacher) req.getSession().getAttribute("user");
			tId = teacher.getTeacherid();
		}
		List<List<SimpleDto>> data = standardService.query(id, tId);
		return JSONArray.fromObject(data);
	}
	
	@ResponseBody
	@RequestMapping(value="/subjects", method=RequestMethod.GET)
	public JSONArray querySubject() throws Exception {
		List<SimpleDto> data = wordService.getSubjects();
		return JSONArray.fromObject(data);
	}
	@ResponseBody
	@RequestMapping(value="/subjectTypes", method=RequestMethod.GET)
	public JSONArray querySubjectTypes() throws Exception {
		List<SimpleDto> data = wordService.getStandardTypes();
		return JSONArray.fromObject(data);
	}
}

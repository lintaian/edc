package com.lps.edc.controller;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lps.edc.dto.SimpleDto;
import com.lps.edc.service.interfaces.SysSchoolServiceIF;

@Controller
@RequestMapping("school")
public class SchoolController {
	@Resource
	private SysSchoolServiceIF schoolService;
	
	@ResponseBody
	@RequestMapping(value="",method=RequestMethod.GET)
	public JSONArray query() throws Exception {
		List<SimpleDto> data = schoolService.get();
		return JSONArray.fromObject(data);
	}
}

package com.lps.edc.controller;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lps.edc.dto.SimpleDto;
import com.lps.edc.service.interfaces.SysWordServiceIF;

@Controller
@RequestMapping("class")
public class ClassController {
	@Resource
	private SysWordServiceIF wordService;
	
	@ResponseBody
	@RequestMapping(value="/grade/{id}", method=RequestMethod.GET)
	public JSONArray query(@PathVariable String id) throws Exception {
		List<SimpleDto> data = wordService.getClasses(id);
		return JSONArray.fromObject(data);
	}
}

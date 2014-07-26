package com.lps.edc.controller;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lps.edc.entity.StatisticalCategory;
import com.lps.edc.service.interfaces.StatisticalCategoryServiceIF;

@Controller
@RequestMapping("statisticalCategory")
public class StatisticalCategoryController {
	@Resource
	private StatisticalCategoryServiceIF statisticalCategoryService;
	@RequestMapping(value="", method=RequestMethod.GET)
	public JSONArray query() throws Exception {
		List<StatisticalCategory> categories = statisticalCategoryService.query();
		return JSONArray.fromObject(categories);
	}
}

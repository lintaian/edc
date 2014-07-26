package com.lps.edc.service.interfaces;

import java.util.List;

import com.lps.edc.entity.StatisticalCategory;

public interface StatisticalCategoryServiceIF {
	public List<StatisticalCategory> query() throws Exception;
}

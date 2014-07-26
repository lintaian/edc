package com.lps.edc.dao.interfaces;

import java.util.List;

import com.lps.edc.entity.StatisticalCategory;

public interface StatisticalCategoryDaoIF {
	public List<StatisticalCategory> query() throws Exception;
}

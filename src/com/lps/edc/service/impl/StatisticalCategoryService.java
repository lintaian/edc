package com.lps.edc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.lps.edc.dao.interfaces.StatisticalCategoryDaoIF;
import com.lps.edc.entity.StatisticalCategory;
import com.lps.edc.service.interfaces.StatisticalCategoryServiceIF;

@Repository
@Service("statisticalCategoryService")
public class StatisticalCategoryService implements StatisticalCategoryServiceIF {
	@Resource
	private StatisticalCategoryDaoIF statisticalCategoryDao;
	@Override
	public List<StatisticalCategory> query() throws Exception {
		return statisticalCategoryDao.query();
	}
}

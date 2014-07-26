package com.lps.edc.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lps.edc.dao.interfaces.StatisticalCategoryDaoIF;
import com.lps.edc.entity.StatisticalCategory;

@Repository
public class StatisticalCategoryDao extends BaseDao implements StatisticalCategoryDaoIF {
	@SuppressWarnings("unchecked")
	@Override
	public List<StatisticalCategory> query() throws Exception {
		String hql = "from StatisticalCategory";
		List<StatisticalCategory> categories = (List<StatisticalCategory>)getHibernateTemplate().find(hql);
		return categories;
	}
}

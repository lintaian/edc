package com.lps.edc.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lps.edc.dao.interfaces.SysSchoolDaoIF;
import com.lps.edc.dto.SimpleDto;

@Repository
public class SysSchoolDao extends BaseDao implements SysSchoolDaoIF {
	@SuppressWarnings("unchecked")
	@Override
	public List<SimpleDto> get() throws Exception {
		String hql = "select new com.lps.edc.dto.SimpleDto(schoolId,schoolName) from SysSchool a";
		List<SimpleDto> schools = (List<SimpleDto>)getHibernateTemplate().find(hql);
		return schools;
	}
}

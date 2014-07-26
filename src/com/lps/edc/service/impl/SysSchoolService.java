package com.lps.edc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.lps.edc.dao.interfaces.SysSchoolDaoIF;
import com.lps.edc.dto.SimpleDto;
import com.lps.edc.service.interfaces.SysSchoolServiceIF;

@Service("schoolService")
@Repository
public class SysSchoolService implements SysSchoolServiceIF {

	@Resource
	private SysSchoolDaoIF schoolDao;
	
	@Override
	public List<SimpleDto> get() throws Exception {
		return schoolDao.get();
	}
}

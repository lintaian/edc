package com.lps.edc.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.lps.edc.dao.interfaces.SysTeacherDaoIF;
import com.lps.edc.entity.SysTeacher;
import com.lps.edc.service.interfaces.SysTeacherServiceIF;

@Service("teacherService")
@Repository
public class SysTeacherService implements SysTeacherServiceIF {
	@Resource
	private SysTeacherDaoIF teacherDao;
	
	@Override
	public SysTeacher get(String name) throws Exception {
		return teacherDao.get(name);
	}
}

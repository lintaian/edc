package com.lps.edc.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.lps.edc.dao.interfaces.SysTeacherDaoIF;
import com.lps.edc.entity.SysTeacher;

@Repository
public class SysTeacherDao extends BaseDao implements SysTeacherDaoIF {

	@SuppressWarnings("unchecked")
	@Override
	public SysTeacher get(String name) throws Exception {
		String hql = "from SysTeacher a where a.teacherid = ?";
		List<SysTeacher> teachers = (List<SysTeacher>)getHibernateTemplate().find(hql, name);
		SysTeacher teacher = null;
		if (teachers.size() > 0) {
			teacher = teachers.get(0);
		}
		return teacher;
	}
}

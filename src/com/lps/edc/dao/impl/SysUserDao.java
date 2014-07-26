package com.lps.edc.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lps.edc.dao.interfaces.SysUserDaoIF;
import com.lps.edc.entity.SysUser;

@Repository
public class SysUserDao extends BaseDao implements SysUserDaoIF {

	@SuppressWarnings("unchecked")
	@Override
	public SysUser get(String name) throws Exception {
		String hql = "from SysUser a where a.userId = ?";
		List<SysUser> users = (List<SysUser>)getHibernateTemplate().find(hql, name);
		SysUser user = null;
		if (users.size() > 0) {
			user = users.get(0);
		}
		return user;
	}
}

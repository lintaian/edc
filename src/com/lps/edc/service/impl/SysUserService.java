package com.lps.edc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.lps.edc.dao.interfaces.SysUserDaoIF;
import com.lps.edc.entity.SysUser;
import com.lps.edc.service.interfaces.SysUserServiceIF;

@Service("userService")
@Repository
public class SysUserService implements SysUserServiceIF {
	@Resource
	private SysUserDaoIF userDao;
	
	@Override
	public SysUser get(String name) throws Exception {
		return userDao.get(name);
	}
}

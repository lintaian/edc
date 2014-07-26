package com.lps.edc.dao.interfaces;

import com.lps.edc.entity.SysUser;

public interface SysUserDaoIF {
	public SysUser get(String name) throws Exception;
}

package com.lps.edc.service.interfaces;

import com.lps.edc.entity.SysUser;

public interface SysUserServiceIF {
	public SysUser get(String name) throws Exception;
}

package com.lps.edc.dao.interfaces;

import java.util.List;

import com.lps.edc.entity.InfExamStandard;

public interface InfExamStandardDaoIF {
	public void add(InfExamStandard examStandard) throws Exception;
	public void update(InfExamStandard standard) throws Exception;
	public void del(InfExamStandard standard) throws Exception;
	public List<InfExamStandard> query(String schoolId, String teacherId) throws Exception;
}

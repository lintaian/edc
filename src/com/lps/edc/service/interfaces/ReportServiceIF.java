package com.lps.edc.service.interfaces;

import java.util.List;

import com.lps.edc.dto.ClassStructDto;
import com.lps.edc.dto.ExamDto;
import com.lps.edc.dto.StandardClassDto;
import com.lps.edc.dto.TongJiProDto;

public interface ReportServiceIF {
	public String getUrl(String param) throws Exception;
	public List<ExamDto> queryExam(List<String> ids, String batchId,String schoolId,String teacherId) throws Exception;
	public List<ClassStructDto> queryClass(List<String> ids, String schoolId, String gradeId) throws Exception;
	public List<TongJiProDto> queryProject(String ids) throws Exception;
	public List<StandardClassDto> queryStandard(String ids) throws Exception;
}

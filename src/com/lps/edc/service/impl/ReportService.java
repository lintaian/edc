package com.lps.edc.service.impl;

import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.lps.edc.dao.interfaces.ReportDaoIF;
import com.lps.edc.dto.ClassStructDto;
import com.lps.edc.dto.ExamDto;
import com.lps.edc.dto.StandardClassDto;
import com.lps.edc.dto.TongJiProDto;
import com.lps.edc.service.interfaces.ReportServiceIF;
import com.lps.edc.util.HttpHelper;
import com.lps.edc.util.ReportConfig;

@Service("reportService")
@Repository
public class ReportService implements ReportServiceIF {
	private ReportConfig config;
	@Resource
	private ReportDaoIF reportDao;
	@SuppressWarnings("deprecation")
	@Override
	public String getUrl(String param) throws Exception {
		param = URLEncoder.encode(param, HTTP.UTF_8);
		StringEntity se = new StringEntity(param);
		byte[] rs = HttpHelper.post(config.getUrl(), se, "application/json", "text/html; charset=utf-8");
		return new String(rs, "utf-8");
	}
	@Override
	public List<ExamDto> queryExam(List<String> ids, String batchId,String schoolId,String teacherId) throws Exception {
		return reportDao.queryExam(ids, batchId, schoolId, teacherId);
	}
	@Override
	public List<ClassStructDto> queryClass(List<String> ids, String schoolId, String gradeId) throws Exception {
		return reportDao.queryClass(ids, schoolId, gradeId);
	}
	@Override
	public List<TongJiProDto> queryProject(String ids) throws Exception {
		return reportDao.queryProject(ids);
	}
	@Override
	public List<StandardClassDto> queryStandard(String ids) throws Exception {
		return reportDao.queryStandard(ids);
	}
	@Resource(name="reportConfig")
	public void setConfig(ReportConfig config) {
		this.config = config;
	}
}

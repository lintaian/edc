package com.lps.edc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.lps.edc.dao.interfaces.InfExamStandardDaoIF;
import com.lps.edc.dao.interfaces.SysWordDaoIF;
import com.lps.edc.dto.SimpleDto;
import com.lps.edc.dto.StandardDto;
import com.lps.edc.entity.InfExamStandard;
import com.lps.edc.service.interfaces.InfExamStandardServiceIF;

@Service("standarService")
@Repository
public class InfExamStandardService implements InfExamStandardServiceIF {

	@Resource
	private InfExamStandardDaoIF standardDao;
	@Resource
	private SysWordDaoIF wordDao;
	
	@Override
	public void add(InfExamStandard examStandard) throws Exception {
		standardDao.add(examStandard);
	}
	@Override
	public void update(InfExamStandard standard) throws Exception {
		standardDao.update(standard);
	}
	@Override
	public void update(int id, double score) throws Exception {
		standardDao.update(id, score);
	}
	@Override
	public void del(String[] ids) throws Exception {
		for (String id : ids) {
			InfExamStandard s = new InfExamStandard();
			s.setId(Integer.parseInt(id));
			standardDao.del(s);
		}
	}
	@Override
	public List<StandardDto> query(String schoolId, String teacherId)
			throws Exception {
		List<InfExamStandard> standards = standardDao.query(schoolId, teacherId);
		List<List<InfExamStandard>> lists = parse(standards);
		return parse2(lists);
	}
	
	private List<List<InfExamStandard>> parse(List<InfExamStandard> standards) {
		List<List<InfExamStandard>> rs = new ArrayList<List<InfExamStandard>>();
		for (InfExamStandard s : standards) {
			boolean flag = true;
			for (List<InfExamStandard> list : rs) {
				if (list.size() > 0 && list.get(0).getSubjectId().equals(s.getSubjectId()) 
						&& list.get(0).getSubjectTypeId().equals(s.getSubjectTypeId())) {
					list.add(s);
					flag = false;
					break;
				}
			}
			if (flag) {
				List<InfExamStandard> list2 = new ArrayList<InfExamStandard>();
				list2.add(s);
				rs.add(list2);
			}
		}
		return rs;
	}
	private List<StandardDto> parse2(List<List<InfExamStandard>> lists) throws Exception {
		List<StandardDto> rs = new ArrayList<StandardDto>();
		for (List<InfExamStandard> list : lists) {
			StandardDto dto = new StandardDto();
			SimpleDto sd0 = wordDao.getSimpleDto(list.get(0).getSubjectId());
			SimpleDto sd1 = wordDao.getSimpleDto(list.get(0).getSubjectTypeId());
			dto.setSubjectName(sd0.getName());
			dto.setSubjectId(sd0.getId());
			dto.setSubjectTypeName(sd1.getName());
			dto.setSubjectTypeId(sd1.getId());
			for (InfExamStandard s : list) {
				SimpleDto sd = wordDao.getSimpleDto(s.getStandardTypeId());
				sd.setId(String.valueOf(s.getId()));
				if ("一本".equals(sd.getName())) {
					dto.setScore1(s.getScore());
					dto.setStandardId1(s.getId());
					dto.setStandardTypeName1(sd.getName());
				} else if ("本科".equals(sd.getName())) {
					dto.setScore2(s.getScore());
					dto.setStandardId2(s.getId());
					dto.setStandardTypeName2(sd.getName());
				} 
			}
			rs.add(dto);
		}
		return rs;
	}
}

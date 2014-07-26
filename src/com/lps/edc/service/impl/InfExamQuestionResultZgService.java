package com.lps.edc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.lps.edc.dao.interfaces.InfExamQuestionResultZgDaoIF;
import com.lps.edc.entity.InfExamQuestionResultZg;
import com.lps.edc.service.interfaces.InfExamQuestionResultZgServiceIF;

@Repository
@Service("questionResultZgService")
public class InfExamQuestionResultZgService implements InfExamQuestionResultZgServiceIF {
	@Resource
	private InfExamQuestionResultZgDaoIF questionResutlZgDao;
	@Override
	public void update(InfExamQuestionResultZg zg) throws Exception {
		questionResutlZgDao.del(zg);
		questionResutlZgDao.save(zg);
	}
}

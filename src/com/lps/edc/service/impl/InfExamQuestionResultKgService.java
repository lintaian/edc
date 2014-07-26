package com.lps.edc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.lps.edc.dao.interfaces.InfExamQuestionResultKgDaoIF;
import com.lps.edc.entity.InfExamQuestionResultKg;
import com.lps.edc.service.interfaces.InfExamQuestionResultKgServiceIF;

@Repository
@Service("questionResultKgService")
public class InfExamQuestionResultKgService implements InfExamQuestionResultKgServiceIF {
	@Resource
	private InfExamQuestionResultKgDaoIF questionResutlKgDao;
	@Override
	public void update(InfExamQuestionResultKg kg) throws Exception {
		questionResutlKgDao.del(kg);
		questionResutlKgDao.save(kg);
	}
}

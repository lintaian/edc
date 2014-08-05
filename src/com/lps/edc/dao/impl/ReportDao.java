package com.lps.edc.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.lps.edc.dao.interfaces.ReportDaoIF;
import com.lps.edc.dto.ClassStructDto;
import com.lps.edc.dto.ExamDto;
import com.lps.edc.dto.StandardClassDto;
import com.lps.edc.dto.TongJiProDto;
@Repository
public class ReportDao extends BaseDao implements ReportDaoIF {

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<ExamDto> queryExam(List<String> ids, String batchId,String schoolId,String teacherId) throws Exception {
		String sql = "select a.BatchId as batchid, a.BatchName as batchname, a.ExamId as examid, "
				+ "a.ExamName as examname, a.SubjectId as subjectid, a.WordInfo as subjectname, "
				+ "a.SchoolName as schoolname, a.ExamTime as examtime, "
				+ "b.score1 as standscore1, b.score2 as standscore2 from "
				+ "(select distinct a.BatchId, b.BatchName, a.ExamId, a.ExamName, a.SubjectId, c.WordInfo, "
				+ "d.SchoolName, a.ExamTime from Inf_Exam_Subject a, Inf_Exam_Batch b, Sys_Word c, Sys_School d "
				+ "where a.ExamId in (:ids) "
				+ "and a.BatchId = :batchId "
				+ "and d.SchoolID = :schoolId and a.BatchId = b.BatchId "
				+ "and a.SubjectId = c.WordId) as a left join "
				+ "(select distinct case when a.SchoolId<>null then a.SchoolId else b.SchoolId end as schoolId, "
				+ "case when a.TeacherId<>null then a.TeacherId else b.TeacherId end as teacherId, "
				+ "case when a.SubjectId<>null then a.SubjectId else b.SubjectId end as subjectId, "
				+ "a.Score as score1, b.Score as score2 from "
				+ "(select a.SchoolId, a.TeacherId, a.SubjectId, a.Score from Inf_Exam_Standard a, Sys_Word b "
				+ "where a.StandardTypeId = b.WordId and b.WordInfo = '一本') as a full outer join "
				+ "(select  a.SchoolId, a.TeacherId, a.SubjectId, a.Score from Inf_Exam_Standard a, Sys_Word b "
				+ "where a.StandardTypeId = b.WordId and b.WordInfo = '本科') as b on "
				+ "a.SchoolId = b.SchoolId and a.TeacherId = b.TeacherId and a.SubjectId = b.SubjectId) as b "
				+ "on a.SubjectId = b.SubjectId and b.SchoolID = b.SchoolId and b.TeacherId = :teacherId";
		SQLQuery q = getSession(true).createSQLQuery(sql);
		q.addScalar("batchid", Hibernate.STRING);
		q.addScalar("batchname", Hibernate.STRING);
		q.addScalar("examid", Hibernate.STRING);
		q.addScalar("examname", Hibernate.STRING);
		q.addScalar("examtime", Hibernate.STRING);
		q.addScalar("schoolname", Hibernate.STRING);
		q.addScalar("subjectid", Hibernate.STRING);
		q.addScalar("subjectname", Hibernate.STRING);
		q.addScalar("standscore1", Hibernate.STRING);
		q.addScalar("standscore2", Hibernate.STRING);
		q.setParameterList("ids", ids);
		q.setString("batchId", batchId);
		q.setString("schoolId", schoolId);
		q.setString("teacherId", teacherId);
		List<ExamDto> dtos = (List<ExamDto>) q.setResultTransformer(Transformers.aliasToBean(ExamDto.class)).list();
		return dtos;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ClassStructDto> queryClass(List<String> ids, String schoolId, String gradeId) throws Exception {
		String hql = "select new com.lps.edc.dto.ClassStructDto(" + 
				"a.schoolId, b.schoolName, a.gradeId, e.wordInfo, a.classId, "
				+ "c.wordInfo, a.classGroupId, d.wordInfo, a.wlTypeId, f.wordInfo) "
				+ "from InfSchoolClass a, SysSchool b, SysWord c, SysWord d, SysWord e, SysWord f "
				+ "where a.classId in (:ids) and a.schoolId = :schoolId "
				+ "and a.gradeId = :gradeId and a.schoolId = b.schoolId and a.classId = c.wordId "
				+ "and a.classGroupId = d.wordId and a.gradeId = e.wordId and a.wlTypeId = f.wordId";
		Query q = getSession(true).createQuery(hql);
		q.setParameterList("ids", ids);
		q.setString("schoolId", schoolId);
		q.setString("gradeId", gradeId);
		List<ClassStructDto> dtos = (List<ClassStructDto>) q.list();
//		List<ClassStructDto> dtos = (List<ClassStructDto>)getHibernateTemplate().find(hql, ids, schoolId, gradeId);
		return dtos;
	}
	@Override
	public List<TongJiProDto> queryProject(String ids) throws Exception {
		return null;
	}
	@Override
	public List<StandardClassDto> queryStandard(String ids) throws Exception {
		return null;
	}
	
}

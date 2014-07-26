package com.lps.edc.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.lps.edc.dao.interfaces.CountDAOIF;
import com.lps.edc.dto.TableDto;

@Repository
public class CountDao extends BaseDao implements CountDAOIF {
	
	@SuppressWarnings("deprecation")
	@Override
	public TableDto getStudentKnowledge(String examid) throws Exception {
		TableDto re = null;
		Session session = getSession(true);
		Connection conn = session.connection();
		ResultSet rs = null;
		CallableStatement call =conn.prepareCall("{Call Proc_Exam_StudentKnowledge(?)}");
		call.setString(1, examid);
		rs = call.executeQuery();
		re = parseData(rs);
		rs.close();
		session.close();
		return re;
	}
	@SuppressWarnings("deprecation")
	@Override
	public TableDto getClassKnowledge(String examid) throws Exception {
		TableDto re = null;
		Session session = getSession(true);
		Connection conn = session.connection();
		ResultSet rs = null;
		CallableStatement call =conn.prepareCall("{Call Proc_Exam_ClassKnowledge(?)}");
		call.setString(1, examid);
		rs = call.executeQuery();
		re = parseData(rs);
		rs.close();
		session.close();
		return re;
	}
	@SuppressWarnings("deprecation")
	@Override
	public TableDto getClassPower(String examid) throws Exception {
		TableDto re = null;
		Session session = getSession(true);
		Connection conn = session.connection();
		ResultSet rs = null;
		CallableStatement call =conn.prepareCall("{Call Proc_Exam_ClassPower(?)}");
		call.setString(1, examid);
		rs = call.executeQuery();
		re = parseData(rs);
		rs.close();
		session.close();
		return re;
	}
	@SuppressWarnings("deprecation")
	@Override
	public TableDto getStudentPower(String examid) throws Exception {
		TableDto re = null;
		Session session = getSession(true);
		Connection conn = session.connection();
		ResultSet rs = null;
		CallableStatement call =conn.prepareCall("{Call Proc_Exam_StudentPower(?)}");
		call.setString(1, examid);
		rs = call.executeQuery();
		re = parseData(rs);
		rs.close();
		session.close();
		return re;
	}
	@Override
	public void countScore(String examid, int type) throws Exception {
		Query q = getSession(true).createSQLQuery("{call Proc_Exam_TongJi(?,?)}");
		q.setString(0, examid);
		q.setInteger(1, type);
		q.executeUpdate();
	}
	@SuppressWarnings("deprecation")
	@Override
	public TableDto getQuestionScore(String gradeId, String examId,
			JSONArray classList, JSONArray questionList, String studentName,
			String examNo) throws Exception {
		TableDto re = null;
		Session session = getSession(true);
		Connection conn = session.connection();
		ResultSet rs = null;
		String sql = parseSql(gradeId, examId, classList, questionList, studentName, examNo, "Proc_Exam_TongJiCheck");
		PreparedStatement ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		re = parseData(rs);
		rs.close();
		session.close();
		return re;
	}
	@SuppressWarnings("deprecation")
	@Override
	public TableDto getAnswerNum(String gradeId, String examId,
			JSONArray classList, JSONArray questionList, String studentName,
			String examNo) throws Exception {
		TableDto re = null;
		Session session = getSession(true);
		Connection conn = session.connection();
		ResultSet rs = null;
		String sql = parseSql(gradeId, examId, classList, questionList, studentName, examNo, "Proc_Exam_TongJiCountCheck");
		PreparedStatement ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		re = parseData(rs);
		rs.close();
		session.close();
		return re;
	}
	@SuppressWarnings("deprecation")
	@Override
	public TableDto getClassAgv(String gradeId, String examId,
			JSONArray classList, JSONArray questionList, String studentName,
			String examNo) throws Exception {
		TableDto re = null;
		Session session = getSession(true);
		Connection conn = session.connection();
		ResultSet rs = null;
		String sql = parseSql(gradeId, examId, classList, questionList, studentName, examNo, "Proc_Exam_TongJiClassCheck");
		PreparedStatement ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		re = parseData(rs);
		rs.close();
		session.close();
		return re;
	}
	@SuppressWarnings("deprecation")
	@Override
	public TableDto getOriginalAnswer(String gradeId, String examId,
			JSONArray classList, JSONArray questionList, String studentName,
			String examNo) throws Exception {
		TableDto re = null;
		Session session = getSession(true);
		Connection conn = session.connection();
		ResultSet rs = null;
		String sql = parseSql(gradeId, examId, classList, questionList, studentName, examNo, "Proc_Exam_TongjiPrimeryCheck");
		PreparedStatement ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		re = parseData(rs);
		rs.close();
		session.close();
		return re;
	}
	
	private TableDto parseData(ResultSet rs) throws Exception {
		TableDto re = new TableDto();
		ResultSetMetaData rsmd = rs.getMetaData();
		List<Object> title = new ArrayList<Object>();
		int length = rsmd.getColumnCount();
		for (int i = 1; i <= length; i++) {
			title.add(rsmd.getColumnName(i));
		}
		re.setTitle(title);
		List<Object> data = new ArrayList<Object>();
		while (rs.next()) {
			List<Object> temp = new ArrayList<Object>();
			for (int i = 1; i <= length; i++) {
				temp.add(rs.getString(i));
			}
			data.add(temp);
		}
		re.setData(data);
		return re;
	}
	
	private String parseSql(String gradeId, String examId,
			JSONArray classList, JSONArray questionList, String studentName,
			String examNo, String proc) {
		StringBuffer sb = new StringBuffer();
		sb.append("declare @p3 dbo.Type_ClassList;");
		for (Object obj : classList) {
			JSONObject json = JSONObject.fromObject(obj);
			sb.append("insert into @p3 values(N'");
			sb.append(json.getString("id"));
			sb.append("',N'");
			sb.append(json.getString("name"));
			sb.append("');");
		}
		sb.append("declare @p4 dbo.Type_QuestionList;");
		for (Object obj : questionList) {
			JSONObject json = JSONObject.fromObject(obj);
			sb.append("insert into @p4 values(N'");
			sb.append(json.getString("id"));
			sb.append("',N'");
			sb.append(json.getString("name"));
			sb.append("');");
		}
		sb.append("exec ");
		sb.append(proc);
		sb.append(" @In_GradeId=N'");
		sb.append(gradeId);
		sb.append("',@In_ExamId=N'");
		sb.append(examId);
		sb.append("',@In_ClassList=@p3,@In_QuestionList=@p4,@In_StudentName=N'");
		sb.append(studentName);
		sb.append("',@In_ExamNo=N'");
		sb.append(examNo);
		sb.append("';");
		return sb.toString();
	}
}

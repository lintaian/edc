package com.lps.edc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 缺考学生
 * @author lta
 *
 */
@Entity
@Table(name="inf_exam_absstudent")
public class InfExamAbsStudent implements java.io.Serializable {

	private static final long serialVersionUID = 7239741772811735569L;
	private int id;
	private String examno;
	private String examid;
	private Date inserttime;

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "examno", nullable = false, length = 50)
	public String getExamno() {
		return this.examno;
	}

	public void setExamno(String examno) {
		this.examno = examno;
	}

	@Column(name = "examid", nullable = false, length = 50)
	public String getExamid() {
		return this.examid;
	}

	public void setExamid(String examid) {
		this.examid = examid;
	}

	@Column(name = "inserttime", nullable = false, length = 23)
	public Date getInserttime() {
		return this.inserttime;
	}

	public void setInserttime(Date inserttime) {
		this.inserttime = inserttime;
	}
}

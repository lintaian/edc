package com.lps.edc.entity;

import java.sql.Clob;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 主观题原始答案
 * @author lta
 *
 */
@Entity
@Table(name = "Inf_Exam_Question_Result_ZG", catalog = "Lps_Data")
public class InfExamQuestionResultZg implements java.io.Serializable {

	private static final long serialVersionUID = -2971312128470602863L;
	private int id;
	private String examId;
	private String imageId;
	private String questionId;
	private Clob lamby;
	private String result;
	private Date insertTime;

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "ExamId", nullable = false, length = 36)
	public String getExamId() {
		return this.examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	@Column(name = "ImageId", nullable = false, length = 36)
	public String getImageId() {
		return this.imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	@Column(name = "QuestionID", nullable = false, length = 36)
	public String getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	@Column(name = "Lamby")
	public Clob getLamby() {
		return this.lamby;
	}

	public void setLamby(Clob lamby) {
		this.lamby = lamby;
	}

	@Column(name = "result", length = 50)
	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "InsertTime", nullable = false, length = 23)
	public Date getInsertTime() {
		return this.insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

}

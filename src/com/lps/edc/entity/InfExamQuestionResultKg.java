package com.lps.edc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 客观题原始答案
 * @author lta
 *
 */
@Entity
@Table(name = "Inf_Exam_Question_Result_KG")
public class InfExamQuestionResultKg implements java.io.Serializable {

	private static final long serialVersionUID = -132475324576018732L;
	private int id;
	private String examId;
	private String questionId;
	private String imageId;
	private String result;
	private String insertTime;

	@Id
	@GeneratedValue
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "ExamID", nullable = false, length = 36)
	public String getExamId() {
		return this.examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	@Column(name = "QuestionID", nullable = false, length = 36)
	public String getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	@Column(name = "ImageID", nullable = false, length = 50)
	public String getImageId() {
		return this.imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	@Column(name = "Result", length = 20)
	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Column(name = "InsertTime", nullable = false, length = 50)
	public String getInsertTime() {
		return this.insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

}

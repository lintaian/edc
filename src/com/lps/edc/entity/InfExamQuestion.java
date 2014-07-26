package com.lps.edc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 试题
 * @author lta
 *
 */
@Entity
@Table(name="inf_exam_question")
public class InfExamQuestion implements java.io.Serializable {

	private static final long serialVersionUID = -2071106195689788130L;
	private int id;
	private String examid;
	private String contentid;
	private String questionName;
	private String questionInfo;
	private String questionImage;
	private String questionResult;
	private String questionId;
	private Double questionNum;
	private Double difficute;
	private String parentid;
	private Date inserttime;
	private Integer questionOrder;
	private String setQuestionUserId;
	private String checkUserId;
	private String state;
	private String questionTypeId;
	private String scoreTeacherId;
	private String rect;
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "Examid", length = 50)
	public String getExamid() {
		return this.examid;
	}

	public void setExamid(String examid) {
		this.examid = examid;
	}

	@Column(name = "contentid", length = 50)
	public String getContentid() {
		return this.contentid;
	}

	public void setContentid(String contentid) {
		this.contentid = contentid;
	}

	@Column(name = "QuestionName")
	public String getQuestionName() {
		return this.questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	@Column(name = "QuestionInfo")
	public String getQuestionInfo() {
		return this.questionInfo;
	}

	public void setQuestionInfo(String questionInfo) {
		this.questionInfo = questionInfo;
	}

	@Column(name = "QuestionImage")
	public String getQuestionImage() {
		return this.questionImage;
	}

	public void setQuestionImage(String questionImage) {
		this.questionImage = questionImage;
	}

	@Column(name = "QuestionResult")
	public String getQuestionResult() {
		return this.questionResult;
	}

	public void setQuestionResult(String questionResult) {
		this.questionResult = questionResult;
	}

	@Column(name = "QuestionId", length = 50)
	public String getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	@Column(name = "QuestionNum", precision = 53, scale = 0)
	public Double getQuestionNum() {
		return this.questionNum;
	}

	public void setQuestionNum(Double questionNum) {
		this.questionNum = questionNum;
	}

	@Column(name = "Difficute", precision = 53, scale = 0)
	public Double getDifficute() {
		return this.difficute;
	}

	public void setDifficute(Double difficute) {
		this.difficute = difficute;
	}

	@Column(name = "parentid", length = 50)
	public String getParentid() {
		return this.parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	@Column(name = "Inserttime", nullable = false, length = 23)
	public Date getInserttime() {
		return this.inserttime;
	}

	public void setInserttime(Date inserttime) {
		this.inserttime = inserttime;
	}

	@Column(name = "questionOrder")
	public Integer getQuestionOrder() {
		return this.questionOrder;
	}

	public void setQuestionOrder(Integer questionOrder) {
		this.questionOrder = questionOrder;
	}

	@Column(name = "SetQuestionUserId", length = 50)
	public String getSetQuestionUserId() {
		return this.setQuestionUserId;
	}

	public void setSetQuestionUserId(String setQuestionUserId) {
		this.setQuestionUserId = setQuestionUserId;
	}

	@Column(name = "CheckUserId", length = 50)
	public String getCheckUserId() {
		return this.checkUserId;
	}

	public void setCheckUserId(String checkUserId) {
		this.checkUserId = checkUserId;
	}

	@Column(name = "State", length = 50)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "QuestionTypeId", length = 200)
	public String getQuestionTypeId() {
		return this.questionTypeId;
	}

	public void setQuestionTypeId(String questionTypeId) {
		this.questionTypeId = questionTypeId;
	}

	@Column(name = "ScoreTeacherId", length = 50)
	public String getScoreTeacherId() {
		return this.scoreTeacherId;
	}

	public void setScoreTeacherId(String scoreTeacherId) {
		this.scoreTeacherId = scoreTeacherId;
	}

	@Column(name = "Rect", length = 500)
	public String getRect() {
		return this.rect;
	}

	public void setRect(String rect) {
		this.rect = rect;
	}
}

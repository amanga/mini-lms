package com.bunge.lms.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ANSWERS")
public class Answer implements Serializable {

	private static final long serialVersionUID = 8921974956190601164L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ANSWER_ID", unique = true)
	private Long qcId;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "QUESTION_ID", nullable=false)
	private Question question;

	@Column(name = "TITLE")
	private String qcTitle;

	@Column(name = "TITLE_TYPE")
	private String qcTitleType; // img or video or text

	@Column(name = "COMMENTS")
	private String qcComment;

	@Column(name = "DESCRIPTION")
	private String qcDesc;

	@Column(name = "STATUS")
	private Boolean qcFlag;

	@Column(name = "ORDERBY")
	private Integer qcOrder;

	@Column(name = "SELECTED")
	private Boolean qcSelected;

	@Column(name = "CORRECT")
	private Boolean correctFlag;

	public Long getQcId() {
		return qcId;
	}

	public void setQcId(Long qcId) {
		this.qcId = qcId;
	}

	public String getQcTitle() {
		return qcTitle;
	}

	public void setQcTitle(String qcTitle) {
		this.qcTitle = qcTitle;
	}

	public String getQcTitleType() {
		return qcTitleType;
	}

	public void setQcTitleType(String qcTitleType) {
		this.qcTitleType = qcTitleType;
	}

	public String getQcComment() {
		return qcComment;
	}

	public void setQcComment(String qcComment) {
		this.qcComment = qcComment;
	}

	public String getQcDesc() {
		return qcDesc;
	}

	public void setQcDesc(String qcDesc) {
		this.qcDesc = qcDesc;
	}

	public Boolean isQcFlag() {
		return qcFlag;
	}

	public void setQcFlag(Boolean qcFlag) {
		this.qcFlag = qcFlag;
	}

	public Integer getQcOrder() {
		return qcOrder;
	}

	public void setQcOrder(Integer qcOrder) {
		this.qcOrder = qcOrder;
	}

	public Boolean isQcSelected() {
		return qcSelected;
	}

	public void setQcSelected(Boolean qcSelected) {
		this.qcSelected = qcSelected;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Boolean getCorrectFlag() {
		return correctFlag;
	}

	public void setCorrectFlag(Boolean correctFlag) {
		this.correctFlag = correctFlag;
	}

	public Boolean getQcFlag() {
		return qcFlag;
	}

	public Boolean getQcSelected() {
		return qcSelected;
	}

	@Override
	public String toString() {
		return "Answer [qcTitle=" + qcTitle + ", qcTitleType=" + qcTitleType + ", qcComment=" + qcComment + ", qcDesc="
				+ qcDesc + ", qcFlag=" + qcFlag + ", qcOrder=" + qcOrder + ", qcSelected=" + qcSelected
				+ ", correctFlag=" + correctFlag + "]";
	}

}

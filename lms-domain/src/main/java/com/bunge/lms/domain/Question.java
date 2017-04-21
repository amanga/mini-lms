package com.bunge.lms.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "QUESTIONS")
public class Question implements Serializable {

	private static final long serialVersionUID = -7662877538204127550L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "QUESTION_ID", unique = true)
	private Long qId;

	@Column(name = "TITLE")
	private String qTitle;

	@Column(name = "SUB_TITLE")
	private String qSubTitle;

	@Column(name = "TITLE_TYPE")
	private String qTitleType;

	@Column(name = "TYPE")
	private String qType; // single or multi option

	@Column(name = "COMMENTS")
	private String qComment;

	@Column(name = "DESCRIPTION")
	private String qDesc;

	@Column(name = "STATUS")
	private Boolean qFlag;

	@Column(name = "EXPLANATION")
	private String qExplanation;

	@Column(name = "REQUIRED")
	private Boolean qRequired;

	@OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Answer> answers;

	@Column(name = "LEVEL")
	private String level;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "questions")
	private Set<QuestionBlock> questionBlocks;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "questions")
	private Set<Tag> tags;

	public Long getqId() {
		return qId;
	}

	public void setqId(Long qId) {
		this.qId = qId;
	}

	public String getqTitle() {
		return qTitle;
	}

	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}

	public String getqSubTitle() {
		return qSubTitle;
	}

	public void setqSubTitle(String qSubTitle) {
		this.qSubTitle = qSubTitle;
	}

	public String getqTitleType() {
		return qTitleType;
	}

	public void setqTitleType(String qTitleType) {
		this.qTitleType = qTitleType;
	}

	public String getqType() {
		return qType;
	}

	public void setqType(String qType) {
		this.qType = qType;
	}

	public String getqComment() {
		return qComment;
	}

	public void setqComment(String qComment) {
		this.qComment = qComment;
	}

	public String getqDesc() {
		return qDesc;
	}

	public void setqDesc(String qDesc) {
		this.qDesc = qDesc;
	}

	public Boolean isqFlag() {
		return qFlag;
	}

	public void setqFlag(Boolean qFlag) {
		this.qFlag = qFlag;
	}

	public String getqExplanation() {
		return qExplanation;
	}

	public void setqExplanation(String qExplanation) {
		this.qExplanation = qExplanation;
	}

	public Boolean isqRequired() {
		return qRequired;
	}

	public void setqRequired(Boolean qRequired) {
		this.qRequired = qRequired;
	}

	public Set<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	public Boolean getqFlag() {
		return qFlag;
	}

	public Boolean getqRequired() {
		return qRequired;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Set<QuestionBlock> getQuestionBlocks() {
		return questionBlocks;
	}

	public void setQuestionBlocks(Set<QuestionBlock> questionBlocks) {
		this.questionBlocks = questionBlocks;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Question [qTitle=" + qTitle + ", qSubTitle=" + qSubTitle + ", qTitleType=" + qTitleType + ", qType="
				+ qType + ", qComment=" + qComment + ", qDesc=" + qDesc + ", qFlag=" + qFlag + ", qExplanation="
				+ qExplanation + ", qRequired=" + qRequired + ", answers=" + answers + "]";
	}

}

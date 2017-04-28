package com.bunge.lms.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "ASSESSMENT")
public class Assessment implements Serializable {

	private static final long serialVersionUID = -8556082127887586490L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ASSESSMENT_ID", unique = true)
	private Long asmId;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "NOTE")
	private String note;

	@Column(name = "STATUS")
	private Boolean status;

	@Column(name = "COMMENTS")
	private String qcComment;

	@Column(name = "DESCRIPTION")
	private String qcDesc;

	@Column(name = "MINQUESTIONS", columnDefinition = "int default 10")
	private Integer minimumQuestions;

	@Column(name = "MAXQUESTIONS", columnDefinition = "int default 10")
	private Integer maximumQuestions;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "ASSESSMENT_MAPPING", joinColumns = {
			@JoinColumn(name = "ASSESSMENT_ID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "QUESTIONBLOCK_ID", nullable = false, updatable = false) })
	@Fetch(FetchMode.SELECT)
	private List<QuestionBlock> questionBlocks = new ArrayList<QuestionBlock>();

	@Column(name = "SCORE", columnDefinition = "int default 60")
	private Integer passingScore;

	@Column(name = "QB_RANDOM_FLAG")
	private Boolean qbRandomizeFlag;

	@Column(name = "QB_TITLE_FLAG")
	private Boolean qbTitleFlag;

	public Long getAsmId() {
		return asmId;
	}

	public void setAsmId(Long asmId) {
		this.asmId = asmId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
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

	public List<QuestionBlock> getQuestionBlocks() {
		return questionBlocks;
	}

	public void setQuestionBlocks(List<QuestionBlock> questionBlocks) {
		this.questionBlocks = questionBlocks;
	}

	public Integer getMinimumQuestions() {
		return minimumQuestions;
	}

	public void setMinimumQuestions(Integer minimumQuestions) {
		this.minimumQuestions = minimumQuestions;
	}

	public Integer getMaximumQuestions() {
		return maximumQuestions;
	}

	public void setMaximumQuestions(Integer maximumQuestions) {
		this.maximumQuestions = maximumQuestions;
	}

	public Integer getPassingScore() {
		return passingScore;
	}

	public void setPassingScore(Integer passingScore) {
		this.passingScore = passingScore;
	}

	public Boolean getQbRandomizeFlag() {
		return qbRandomizeFlag;
	}

	public void setQbRandomizeFlag(Boolean qbRandomizeFlag) {
		this.qbRandomizeFlag = qbRandomizeFlag;
	}

	public Boolean getQbTitleFlag() {
		return qbTitleFlag;
	}

	public void setQbTitleFlag(Boolean qbTitleFlag) {
		this.qbTitleFlag = qbTitleFlag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Assessment other = (Assessment) obj;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Assessment [title=" + title + ", note=" + note + ", status=" + status + ", questionBlocks="
				+ questionBlocks + "]";
	}

}

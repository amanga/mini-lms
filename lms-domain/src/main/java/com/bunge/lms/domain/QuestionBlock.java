package com.bunge.lms.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
@Table(name = "QUESTIONBLOCK")
public class QuestionBlock implements Serializable {

	private static final long serialVersionUID = 985350371026706462L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "QUESTIONBLOCK_ID", unique = true)
	private Long qbId;

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

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "QUESTIONBLOCK_MAPPING", joinColumns = {
			@JoinColumn(name = "QUESTIONBLOCK_ID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "QUESTION_ID", nullable = false, updatable = false) })
	@Fetch(FetchMode.SELECT)
	private List<Question> questions = new ArrayList<Question>();

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "questionBlocks")
	private Set<Assessment> assessments;

	public Long getQbId() {
		return qbId;
	}

	public void setQbId(Long qbId) {
		this.qbId = qbId;
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

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
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
		QuestionBlock other = (QuestionBlock) obj;
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
		return "QuestionBlock [title=" + title + ", note=" + note + ", status=" + status + ", questions=" + questions
				+ "]";
	}

}

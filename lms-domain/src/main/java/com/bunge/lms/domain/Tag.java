package com.bunge.lms.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "TAGS")
public class Tag implements Serializable {

	private static final long serialVersionUID = -3901905524595339712L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TAG_ID", unique = true)
	private Long tId;

	@Column(name = "TITLE")
	private String title;

	@ManyToOne
	@JoinColumn(name = "PARENTTAG_ID", nullable=true)
	private Tag parentTagId;
	
	
	@OneToMany(mappedBy="parentTagId", fetch=FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	private Set<Tag> parentTagIdCols;
	

	@Column(name = "STATUS")
	private Boolean status;

	@Column(name = "COMMENTS")
	private String comments;

	@Column(name = "DESCRIPTION")
	private String description;

	public Long gettId() {
		return tId;
	}

	public void settId(Long tId) {
		this.tId = tId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Tag getParentTagId() {
		return parentTagId;
	}

	public void setParentTagId(Tag parentTagId) {
		this.parentTagId = parentTagId;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Tag> getParentTagIdCols() {
		return parentTagIdCols;
	}

	public void setParentTagIdCols(Set<Tag> parentTagIdCols) {
		this.parentTagIdCols = parentTagIdCols;
	}

	
}

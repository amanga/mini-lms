package com.bunge.lms.domain;

import java.io.Serializable;
import java.util.HashSet;
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

@Entity
@Table(name = "ASSESSMENT")
public class Assessment implements Serializable{

	private static final long serialVersionUID = -8556082127887586490L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ASSESSMENT_ID", unique = true)
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

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "ASSESSMENT_MAPPING", joinColumns = {
			@JoinColumn(name = "ASSESSMENT_ID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "QUESTIONBLOCK_ID", nullable = false, updatable = false) })
	private Set<QuestionBlock> questionBlocks = new HashSet<QuestionBlock>();

	
}

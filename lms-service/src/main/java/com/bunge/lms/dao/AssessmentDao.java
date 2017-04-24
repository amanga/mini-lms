package com.bunge.lms.dao;

import java.util.List;

import com.bunge.lms.domain.Assessment;

public interface AssessmentDao extends GenericDao<Assessment> {

	public List<Assessment> findByText(String assessmentTitle);
	
}

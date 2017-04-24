package com.bunge.lms.service;

import java.io.FileInputStream;
import java.util.List;

import com.bunge.lms.domain.Assessment;

public interface AssessmentService {

	public void save(FileInputStream fInputstream, String fileName)throws Exception;

	public List<Assessment> findByText(String assessmentText)throws Exception;
	
	public void save(Assessment assessment)throws Exception;
	
	public void update(Assessment assessment)throws Exception;
	
	public List<Assessment> fetchAll() throws Exception;
}

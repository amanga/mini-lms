package com.bunge.lms.service;

import java.util.List;

import com.bunge.lms.domain.Question;

public interface QuestionService {

	public List<Question> findByText(String questionText)throws Exception;
	
	public void save(Question question)throws Exception;
	
	public void update(Question question)throws Exception;
	
	public List<Question> fetchAll() throws Exception;
}

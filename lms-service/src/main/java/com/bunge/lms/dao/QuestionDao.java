package com.bunge.lms.dao;

import java.util.List;

import com.bunge.lms.domain.Question;

public interface QuestionDao extends GenericDao<Question> {
	public List<Question> findByText(String questionText);
}

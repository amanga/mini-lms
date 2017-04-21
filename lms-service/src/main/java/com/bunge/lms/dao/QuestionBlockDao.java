package com.bunge.lms.dao;

import java.util.List;

import com.bunge.lms.domain.Question;
import com.bunge.lms.domain.QuestionBlock;

public interface QuestionBlockDao extends GenericDao<QuestionBlock> {
	public List<QuestionBlock> findByText(String questionBlockTitle);
}

package com.bunge.lms.service;

import java.io.FileInputStream;
import java.util.List;

import com.bunge.lms.domain.QuestionBlock;

public interface QuestionBlockService {
	
	public void save(FileInputStream fInputstream, String fileName)throws Exception;

	public List<QuestionBlock> findByText(String questionText)throws Exception;
	
	public void save(QuestionBlock question)throws Exception;
	
	public void update(QuestionBlock question)throws Exception;
	
	public List<QuestionBlock> fetchAll() throws Exception;
}

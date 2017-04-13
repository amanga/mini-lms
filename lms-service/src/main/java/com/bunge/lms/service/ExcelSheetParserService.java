package com.bunge.lms.service;

import java.io.FileInputStream;
import java.util.List;

import com.bunge.lms.domain.Question;

public interface ExcelSheetParserService {
	
	public List<Question> readQuestionFromExcel(FileInputStream fileInputStream, String filePath) throws Exception;

	public List<Question> readQuestionsFromExcel(String filePath)throws Exception;
		
}

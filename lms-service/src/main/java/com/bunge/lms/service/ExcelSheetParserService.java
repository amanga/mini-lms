package com.bunge.lms.service;

import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import com.bunge.lms.domain.Question;
import com.bunge.lms.domain.QuestionBlock;

public interface ExcelSheetParserService {
	
	public Map<QuestionBlock, List<Question>> readQuestionFromExcel(FileInputStream fileInputStream, String filePath) throws Exception;

	public Map<QuestionBlock, List<Question>> readQuestionsFromExcel(String filePath)throws Exception;
		
}

package com.bunge.lms.service;

import java.util.List;

import com.bunge.lms.domain.Question;

public interface ExcelSheetParserService {

	public List<Question> readQuestionsFromExcel(String filePath)throws Exception;
		
}

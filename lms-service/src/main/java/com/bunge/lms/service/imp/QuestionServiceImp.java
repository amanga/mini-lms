package com.bunge.lms.service.imp;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bunge.lms.dao.QuestionDao;
import com.bunge.lms.domain.Question;
import com.bunge.lms.service.ExcelSheetParserService;
import com.bunge.lms.service.QuestionService;

@Service
public class QuestionServiceImp implements QuestionService {

	@Autowired
	private ExcelSheetParserService excelSheetParserService;

	@Autowired
	private QuestionDao questionDao;

	@Override
	@Transactional
	public void save(FileInputStream fInputstream, String fileName) throws Exception {
		List<Question> qCol = excelSheetParserService.readQuestionFromExcel(fInputstream, fileName);
		
		//only process below statements if excel file has questions.
		if(!qCol.isEmpty()){
			Iterator<Question> qItr = qCol.iterator();
			while(qItr.hasNext()){
				Question question = qItr.next();
				if(question != null){
					save(question);
				}
			}
		}
	}

	@Override
	@Transactional
	public void save(Question question) throws Exception {
		questionDao.persist(question);
	}

	@Override
	@Transactional
	public void update(Question question) throws Exception {
		questionDao.merge(question);
	}

	@Override
	@Transactional
	public List<Question> fetchAll() throws Exception {
		return questionDao.findAll();
	}

	@Override
	@Transactional
	public List<Question> findByText(String questionText) throws Exception {
		System.out.println("Inside findByText service...");
		return questionDao.findByText(questionText);
	}

}

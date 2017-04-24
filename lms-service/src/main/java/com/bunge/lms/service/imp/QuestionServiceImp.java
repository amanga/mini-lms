package com.bunge.lms.service.imp;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bunge.lms.dao.QuestionDao;
import com.bunge.lms.domain.Assessment;
import com.bunge.lms.domain.Question;
import com.bunge.lms.domain.QuestionBlock;
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
//		Map<QuestionBlock, List<Question>> qBlockQCol = excelSheetParserService.readQuestionFromExcel(fInputstream, fileName);
		
		Map<Assessment, Map<QuestionBlock,List<Question>>> asm = excelSheetParserService.readQuestionFromExcel(fInputstream, fileName);

		Set<Assessment> asmKeyCol = asm.keySet();
		Iterator<Assessment> asmItr = asmKeyCol.iterator();
		while(asmItr.hasNext()){
			List<Question> qCol = getQuestions(asm.get(asmItr.next()));
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
	}
	
	private List<Question> getQuestions(Map<QuestionBlock,List<Question>> qBlockCol){
		List<Question> questionCol = new ArrayList<Question>();
		//Iterate through all questionblocks 
		Set<QuestionBlock> qBlockSetCol = qBlockCol.keySet();
		Iterator<QuestionBlock> qbItr = qBlockSetCol.iterator();
		while(qbItr.hasNext()){
			QuestionBlock questionBlock  = qbItr.next();
			questionCol.addAll(qBlockCol.get(questionBlock));
		}
		return questionCol;
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

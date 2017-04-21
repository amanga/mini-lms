package com.bunge.lms.service.imp;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bunge.lms.dao.QuestionBlockDao;
import com.bunge.lms.domain.Question;
import com.bunge.lms.domain.QuestionBlock;
import com.bunge.lms.service.ExcelSheetParserService;
import com.bunge.lms.service.QuestionBlockService;

@Service
public class QuestionBlockServiceImp implements QuestionBlockService {

	@Autowired
	private ExcelSheetParserService excelSheetParserService;

	@Autowired
	private QuestionBlockDao questionBlockDao;

	@Override
	@Transactional
	public void save(FileInputStream fInputstream, String fileName) throws Exception {
		Map<QuestionBlock, List<Question>> qBlockQCol = excelSheetParserService.readQuestionFromExcel(fInputstream, fileName);
		
		Set<QuestionBlock> qbCol = qBlockQCol.keySet();
		Iterator<QuestionBlock> qbItr = qbCol.iterator();
		while(qbItr.hasNext()){
			QuestionBlock questionBlock  = qbItr.next();
			List<Question> questions = qBlockQCol.get(questionBlock);
			questionBlock.setQuestions(new HashSet<>(questions));
			if(questionBlock!=null){
				save(questionBlock);
			}
		}
	}
	
	@Override
	@Transactional
	public void save(QuestionBlock question) throws Exception {
		questionBlockDao.persist(question);
	}

	@Override
	@Transactional
	public void update(QuestionBlock question) throws Exception {
		questionBlockDao.merge(question);
	}

	@Override
	@Transactional
	public List<QuestionBlock> fetchAll() throws Exception {
		return questionBlockDao.findAll();
	}

	@Override
	@Transactional
	public List<QuestionBlock> findByText(String questionText) throws Exception {
		return questionBlockDao.findByText(questionText);
	}

}

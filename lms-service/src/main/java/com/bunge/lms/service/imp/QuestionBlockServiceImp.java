package com.bunge.lms.service.imp;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bunge.lms.dao.QuestionBlockDao;
import com.bunge.lms.domain.Assessment;
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
//		Map<QuestionBlock, List<Question>> qBlockQCol = excelSheetParserService.readQuestionFromExcel(fInputstream, fileName);
		
		Map<Assessment, Map<QuestionBlock,List<Question>>> asm = excelSheetParserService.readQuestionFromExcel(fInputstream, fileName);
		
		Set<Assessment> asmKeyCol = asm.keySet();
		Iterator<Assessment> asmItr = asmKeyCol.iterator();
		while(asmItr.hasNext()){
			Map<QuestionBlock, List<Question>> qBlockCol = asm.get(asmItr.next());
			
			Set<QuestionBlock> qBlockSet = qBlockCol.keySet();
			Iterator<QuestionBlock> qbItr = qBlockSet.iterator();
			while(qbItr.hasNext()){
				QuestionBlock questionBlock  = qbItr.next();
				List<Question> qList =  qBlockCol.get(questionBlock);
				questionBlock.setQuestions(qList);
				if(questionBlock!=null){
					save(questionBlock);
				}
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

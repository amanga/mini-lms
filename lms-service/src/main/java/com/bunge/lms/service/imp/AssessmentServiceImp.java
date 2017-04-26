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

import com.bunge.lms.dao.AssessmentDao;
import com.bunge.lms.domain.Assessment;
import com.bunge.lms.domain.Question;
import com.bunge.lms.domain.QuestionBlock;
import com.bunge.lms.service.AssessmentService;
import com.bunge.lms.service.ExcelSheetParserService;

@Service
@Transactional
public class AssessmentServiceImp implements AssessmentService {

	@Autowired
	private ExcelSheetParserService excelSheetParserService;

	@Autowired
	private AssessmentDao assessmentDao;

	@Override
	@Transactional
	public void save(FileInputStream fInputstream, String fileName) throws Exception {
		Map<Assessment, Map<QuestionBlock, List<Question>>> asm = excelSheetParserService
				.readQuestionFromExcel(fInputstream, fileName);

		Set<Assessment> asmKeyCol = asm.keySet();
		Iterator<Assessment> asmItr = asmKeyCol.iterator();
		while (asmItr.hasNext()) {
			Assessment asmKey = asmItr.next();
			Map<QuestionBlock, List<Question>> qBlockCol = asm.get(asmKey);
			Set<QuestionBlock> qBlockSet = qBlockCol.keySet();
			Iterator<QuestionBlock> qbItr = qBlockSet.iterator();
			List<QuestionBlock> questionBlocks = new ArrayList<QuestionBlock>();

			while (qbItr.hasNext()) {
				QuestionBlock questionBlock = qbItr.next();
				List<Question> qList = qBlockCol.get(questionBlock);
				questionBlock.setQuestions(qList);
				questionBlocks.add(questionBlock);
			}
			asmKey.setQuestionBlocks(new ArrayList<QuestionBlock>(questionBlocks));
			if (asmKey != null) {
				save(asmKey);
			}
		}
	}

	@Override
	@Transactional
	public List<Assessment> findByText(String assessmentText) throws Exception {
		return assessmentDao.findByText(assessmentText);
	}

	@Override
	@Transactional
	public void save(Assessment assessment) throws Exception {
		assessmentDao.persist(assessment);
	}

	@Override
	@Transactional
	public void update(Assessment assessment) throws Exception {
		assessmentDao.merge(assessment);

	}

	@Override
	@Transactional
	public List<Assessment> fetchAll() throws Exception {
		return assessmentDao.findAll();
	}

}

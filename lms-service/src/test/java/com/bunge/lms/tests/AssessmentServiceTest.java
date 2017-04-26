package com.bunge.lms.tests;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.bunge.lms.domain.Assessment;
import com.bunge.lms.domain.Question;
import com.bunge.lms.domain.QuestionBlock;
import com.bunge.lms.service.AssessmentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:application-context_test.xml", "classpath:persistence-context_test.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})	
public class AssessmentServiceTest {

	@Autowired
	private AssessmentService assessmentService;
	
	@Before
	public void init() {
	}

	public void testAssessmentDao() {
		try {
			String excelSheetFilePath = getClass().getClassLoader().getResource("Java_question_1.xlsx").getPath();
			File excelFile = new File(excelSheetFilePath);
			FileInputStream fInputStream = new FileInputStream(excelFile);
			assessmentService.save(fInputStream, excelFile.getName());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadAllAssessments(){
		
		try {
			List<Assessment> asmList = assessmentService.fetchAll();
			System.out.println("# of Assessments are "+asmList.size());
			Iterator<Assessment> asmItr = asmList.iterator();
			while(asmItr.hasNext()){
				Assessment asm = asmItr.next();
				System.out.println("Assessment Name := "+asm.getTitle());
				List<QuestionBlock> qBlockSet = asm.getQuestionBlocks();
				System.out.println("# of QuestionBlocks are "+qBlockSet.size());
				Iterator<QuestionBlock> qBlockItr = qBlockSet.iterator();
				while(qBlockItr.hasNext()){
					QuestionBlock qBlock = qBlockItr.next();
					System.out.println("Question Block Name := "+qBlock.getTitle());
					List<Question> qList = qBlock.getQuestions();
					System.out.println("# Questions are "+qList.size());
				}
			}
			System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAssessment(){
		testAssessmentDao();
		loadAllAssessments();
	}
	
	@After
	public void destroy() {

	}
}

package com.bunge.lms.tests;

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

import com.bunge.lms.client.AssessmentResponse;
import com.bunge.lms.controller.AssessmentController;
import com.bunge.lms.domain.Assessment;
import com.bunge.lms.domain.QuestionBlock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context_test.xml", "classpath:persistence-context_test.xml",
		"classpath:web-context_test.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
public class AssessmentControllerTest {
	
	@Autowired
	private AssessmentController asmController;
	
	@Before
	public void before(){
		System.out.println("Before");
	}
	
	@Test
	public void testAssessmentController(){
		System.out.println("Initiating Assessment Controller.");
		
		AssessmentResponse asmResponse = asmController.fetchAllAssessments();
		
		List<Assessment> asmList = asmResponse.getAssessments();
		System.out.println("# of Assessments are "+asmList.size());
		Iterator<Assessment> asmItr = asmList.iterator();
		while(asmItr.hasNext()){
			Assessment asm = asmItr.next();
			System.out.println("Assessment Name := "+asm.getTitle());
			List<QuestionBlock> qBlockSet = asm.getQuestionBlocks();
			System.out.println("# of QuestionBlocks are "+qBlockSet.size());
			System.out.println("");
		}
	}
	
	@After
	public void destroy(){
		System.out.println("Destroy");
	}

}

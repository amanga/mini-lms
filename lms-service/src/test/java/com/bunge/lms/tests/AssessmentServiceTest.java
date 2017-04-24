package com.bunge.lms.tests;

import java.io.File;
import java.io.FileInputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

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

	@Test
	public void testQuestionDao() {
		try {
			String excelSheetFilePath = getClass().getClassLoader().getResource("Java_question_1.xlsx").getPath();
			File excelFile = new File(excelSheetFilePath);
			FileInputStream fInputStream = new FileInputStream(excelFile);
			assessmentService.save(fInputStream, excelFile.getName());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void destroy() {

	}
}

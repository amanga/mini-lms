package com.bunge.lms.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.bunge.lms.service.ExcelSheetParserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:application-context_test.xml", "classpath:persistence-context_test.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})	
@Transactional
public class ExcelSheetServiceTest {

	@Autowired
	private ExcelSheetParserService excelSheetService;
	
	@Before
	public void init() {
	}

	@Test
	public void testQuestionDao() {
		try {
			String excelSheetFilePath = getClass().getClassLoader().getResource("Java_question_1.xlsx").getPath();
			System.out.println(excelSheetFilePath);
			excelSheetService.readQuestionsFromExcel(excelSheetFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void destroy() {

	}
}

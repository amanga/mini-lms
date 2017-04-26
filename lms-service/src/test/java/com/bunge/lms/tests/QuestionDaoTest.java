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
import org.springframework.transaction.annotation.Transactional;

import com.bunge.lms.domain.Question;
import com.bunge.lms.service.QuestionService;

import ch.qos.logback.core.net.SyslogOutputStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:application-context_test.xml", "classpath:persistence-context_test.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})	
@Transactional
public class QuestionDaoTest {

	@Autowired
	private QuestionService questionService;
	
	@Before
	public void init() {
	}

	
	public void testInsertQuestions() {
		try {
			String excelSheetFilePath = getClass().getClassLoader().getResource("Java_question_1.xlsx").getPath();
			File excelFile = new File(excelSheetFilePath);
			FileInputStream fInputStream = new FileInputStream(excelFile);
			questionService.save(fInputStream, excelFile.getName());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void testReadQuestions(){
		try {
			List<Question> questions =  questionService.fetchAll();
			System.out.println("# of questions :"+ questions.size());
			Iterator<Question> qIter = questions.iterator();
			while(qIter.hasNext()){
				Question question = qIter.next();
				System.out.println("Question: " + question.getqTitle());
				System.out.print(" \t # of answer :"+ question.getAnswers().size());
				System.out.println("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQuestions(){
		testInsertQuestions();
		testReadQuestions();
	}

	@After
	public void destroy() {

	}
}

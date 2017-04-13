package com.bunge.lms.tests;

import java.util.Iterator;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.bunge.lms.domain.Tag;
import com.bunge.lms.service.TagService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context_test.xml",
		"classpath:persistence-context_test.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
@Transactional
public class TagEntityTests {

	@Autowired
	private TagService tagService;

	@Before
	public void init() {
	}
	
	
	@Test
	public void tagTestSuite(){
		insertJavaTagTest();
		insertJavaChildThreadTagTest();
		insertJavaChildExceptionTagTest();
		insertJavaChildCollectionTagTest();
		printJavaTagTest();
	}

	
	public void insertJavaTagTest() {

		try {
			Tag tagJava = new Tag();
			tagJava.setTitle("Java");
			tagJava.setStatus(true);
			tagJava.setDescription("Core java related question");

			persistTag(tagJava);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void insertJavaChildThreadTagTest() {
		try {
			Tag tagJava = tagService.findByTitle("Java");

			Tag tagMultithreading = new Tag();
			tagMultithreading.setTitle("Multithreading");
			tagMultithreading.setStatus(true);
			tagMultithreading.setDescription("Multithreading related questions");
			tagMultithreading.setParentTagId(tagJava);

			persistTag(tagMultithreading);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void insertJavaChildExceptionTagTest() {
		try {
			Tag tagJava = tagService.findByTitle("Java");

			Tag tagMultithreading = new Tag();
			tagMultithreading.setTitle("Exception Handling");
			tagMultithreading.setStatus(true);
			tagMultithreading.setDescription("Exception related questions");
			tagMultithreading.setParentTagId(tagJava);

			persistTag(tagMultithreading);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void insertJavaChildCollectionTagTest() {
		try {
			Tag tagJava = tagService.findByTitle("Java");

			Tag tagMultithreading = new Tag();
			tagMultithreading.setTitle("Collection");
			tagMultithreading.setStatus(true);
			tagMultithreading.setDescription("Collection related questions");
			tagMultithreading.setParentTagId(tagJava);

			persistTag(tagMultithreading);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void persistTag(Tag tag) {
		try {
			Tag tagObj = tagService.findByTitle(tag.getTitle().trim());
			if (StringUtils.isEmpty(tagObj)) {
				tagService.save(tag);
			} else {
				tagService.update(tag);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void printJavaTagTest(){
		System.out.println(">>>>>>>>>>>>>>>>>Print Java tag<<<<<<<<<<<<<<<<<<<<<<<<<");
		try{
			Tag tagJava = tagService.findByTitle("Java");
			System.out.println(tagJava.getTitle());
			Set<Tag> colTags = tagJava.getParentTagIdCols();
			Iterator<Tag> tItr =  colTags.iterator();
			while(tItr.hasNext()){
				Tag childTags = tItr.next();
				System.out.println("\t "+childTags.getTitle());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		
	}

}

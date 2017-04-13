package com.bunge.lms.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bunge.lms.dao.TagsDao;
import com.bunge.lms.domain.Tag;
import com.bunge.lms.service.TagService;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class TagServiceImp implements TagService {

	@Autowired
	private TagsDao tagDao;
	
	@Override
	@Transactional
	public void save(Tag tag) throws Exception {
		System.out.println("Insert Tag");
		tagDao.persist(tag);
	}

	@Override
	@Transactional
	public void update(Tag tag) throws Exception {
		System.out.println("Update Tag");
		tagDao.persist(tag);
	}
	
	@Override
	@Transactional
	public List<Tag> fetchAll() throws Exception {
		return tagDao.findAll();
	}

	@Override
	@Transactional
	public Tag findById(Long tagId) throws Exception {
		return tagDao.find(tagId);
	}

	@Override
	@Transactional
	public Tag findByTitle(String title) throws Exception {
		return tagDao.findByTitle(title);
	}

	
}

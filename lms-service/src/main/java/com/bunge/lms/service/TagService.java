package com.bunge.lms.service;

import java.util.List;

import com.bunge.lms.domain.Tag;

public interface TagService {

	public void save(Tag tag)throws Exception;
	
	public void update(Tag tag)throws Exception;
	
	public List<Tag> fetchAll() throws Exception;
	
	public Tag findById(Long tagId) throws Exception;
	
	public Tag findByTitle(String title) throws Exception;
	
}

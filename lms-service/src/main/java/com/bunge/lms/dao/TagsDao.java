package com.bunge.lms.dao;

import com.bunge.lms.domain.Tag;

public interface TagsDao extends GenericDao<Tag> {

	public Tag findByTitle(String title)throws Exception;
	
}

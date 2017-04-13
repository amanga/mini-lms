package com.bunge.lms.dao.imp;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.bunge.lms.dao.TagsDao;
import com.bunge.lms.domain.Tag;

@Component
@SuppressWarnings("unchecked")
public class TagsDaoImp extends AbstractDao<Tag> implements TagsDao {

	@Override
	public Tag findByTitle(String title) throws Exception {
		return (Tag) createCriteria(Restrictions.eq("title", title)).uniqueResult();
	}


}

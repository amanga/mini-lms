package com.bunge.lms.dao.imp;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.bunge.lms.dao.QuestionBlockDao;
import com.bunge.lms.domain.QuestionBlock;

@Component
@SuppressWarnings("unchecked")
public class QuestionBlockDaoImp extends AbstractDao<QuestionBlock> implements QuestionBlockDao {
	
	public List<QuestionBlock> findByText(String questionText) {
		return createCriteria(Restrictions.eq("title", questionText)).list();
	}

}

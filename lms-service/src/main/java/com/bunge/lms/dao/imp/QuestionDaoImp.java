package com.bunge.lms.dao.imp;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.bunge.lms.dao.QuestionDao;
import com.bunge.lms.domain.Question;

@Component
@SuppressWarnings("unchecked")
public class QuestionDaoImp extends AbstractDao<Question> implements QuestionDao {
	
	public List<Question> findByText(String questionText) {
		return createCriteria(Restrictions.eq("qTitle", questionText)).list();
	}

}

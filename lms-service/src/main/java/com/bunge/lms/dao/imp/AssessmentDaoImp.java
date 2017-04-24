package com.bunge.lms.dao.imp;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.bunge.lms.dao.AssessmentDao;
import com.bunge.lms.domain.Assessment;

@Component
@SuppressWarnings("unchecked")
public class AssessmentDaoImp extends AbstractDao<Assessment> implements AssessmentDao {

	@Override
	public List<Assessment> findByText(String assessmentTitle) {
		return createCriteria(Restrictions.eq("title", assessmentTitle)).list();
	}

}

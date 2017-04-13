package com.bunge.lms.dao.imp;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;

import com.bunge.lms.dao.GenericDao;

@SuppressWarnings("unchecked")
public class AbstractDao<T> implements GenericDao<T> {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Class<T> entityClass;
	
	public AbstractDao() {
		entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public T find(Serializable id) {
		return (T) getSession().get(entityClass, id);
	}

	public List<T> findAll() {
		return createCriteria().list();
	}

	public T persist(T instance) {
		getSession().saveOrUpdate(instance);
		return instance;
	}

	public T merge(T instance) {
		Object obj = getSession().merge(instance);
		return (T) obj;
	}

	public void delete(T instance) {
		getSession().delete(instance);
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	protected Criteria createCriteria(Criterion...criterions){
		return createCriteria(getEntityClass(), criterions);
	}

	protected Criteria createCriteria(Class<?> entityToSearch, Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityToSearch);
		for (Criterion criterion : criterions) {
			criteria.add(criterion);
		}
		return criteria;
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

}

package com.bunge.lms.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T> {
	
	T find(Serializable id);
	
	List<T> findAll();
	
	T persist(T instance);
	
	T merge(T instance);
	
	void delete(T instance);

}

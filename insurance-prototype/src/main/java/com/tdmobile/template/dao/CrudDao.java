package com.tdmobile.template.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class CrudDao<T extends Serializable> {
	
	@PersistenceContext
    protected EntityManager em;	
	
	private Class<T> clazz;
	
	public CrudDao(Class<T> clazz) {
		this.clazz=clazz;
	}

	public List<T> findAll() {
        return em.createQuery(String.format("FROM %s", clazz.getName()), clazz).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findfield(String fieldName,Object field) {
		Query query=em.createQuery(String.format("FROM %s WHERE upper(%s)=:parameter ", clazz.getName(), fieldName), clazz);
		query.setParameter("parameter", field);
		return query.getResultList();
	}
	
	public T findId(Object id) {
        return em.find(clazz, id);
	}
	
	public void persist(T entity) {
        em.persist(entity);
	}
	
	public void merge(T entity) {
        em.merge(entity);
	}
	
	public void remove(T entity) {
        em.remove(entity);
	}
}

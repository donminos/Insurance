package com.tdmobile.template.services;

import java.util.List;

public interface ServiceImp<T> {

    public List<T> findAll();

    public List<T> findfield(String fieldName, Object field);

    public T findId(Object id);

    public void insert(T entity);

    public void edit(T entity);

    public void delete(T entity);
}

package com.chortitzer.cin.model.dao;

import java.util.List;

public interface AbstractDao<T> {

    public void persist(T entity);

    public void merge(T entity);

    public void remove(T entity);

    public List<T> findAll();

    public List<T> getListFromQuery(String jpqlQuery);

}

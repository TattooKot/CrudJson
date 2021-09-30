package com.tattookot.crudJson.repository;

public interface GenericRepository<T, ID>{
    Iterable<T> getAll();
    T getById(ID id);
    T create(T entity);
    T update(T entity);
    void deleteById(ID id);
}

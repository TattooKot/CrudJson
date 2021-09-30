package com;

public interface GenericRepository<T, ID>{
    Iterable<T> getAll();
    T getById(ID id);
    T create(T entity);
    T save(T entity);
    void deleteById(ID id);
}

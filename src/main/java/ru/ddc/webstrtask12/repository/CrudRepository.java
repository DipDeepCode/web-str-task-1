package ru.ddc.webstrtask12.repository;


import java.util.List;

public interface CrudRepository<T, ID> {

    T save(T model);

    List<T> findAll();

    T findById(ID id);

    int update(T t);

    int deleteById(ID id);
}

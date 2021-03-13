package io.github.akkhadka.webstore.model;

import java.util.Collection;
import java.util.List;

public interface Repository<T,Id> {
    void update(T entity);
    void save(T entity);
    void remove(T entity);
    T find(Id id);
    List<T> findAll();


}

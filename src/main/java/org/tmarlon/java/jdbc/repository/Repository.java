package org.tmarlon.java.jdbc.repository;
import java.util.List;

public interface Repository<T> {

    List<T> findAll();

    T finById(Long id);

    void save(T t);

    void delete(Long id);


}

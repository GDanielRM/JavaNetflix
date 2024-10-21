package org.example.dani.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface IDao <T> {
    List<T> findAll() throws SQLException;

    T getById(Integer id) throws SQLException;

    void save(T t) throws SQLException;

    void delete(Integer id) throws SQLException;
}

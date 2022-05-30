package service;

import java.sql.SQLException;
import java.util.List;

public interface GenaraService<T> {
    public List<T> findAll() throws SQLException;

    public void add(T t) throws SQLException;

    public T findById(int id);

    public boolean delete(int id) throws SQLException;

    public boolean update(T t) throws SQLException;



}

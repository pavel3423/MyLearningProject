package project.java.dao.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {

    boolean create(T bean) throws SQLException;

    T read(int id) throws SQLException;

    boolean update(T bean) throws SQLException;

    boolean delete(T bean) throws SQLException;

    List<T> getAll() throws SQLException;

    List<T> getAll(String WHERE) throws SQLException;
}

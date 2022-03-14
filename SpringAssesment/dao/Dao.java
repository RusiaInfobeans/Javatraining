package SpringAssesment.dao;

import java.util.List;

public interface Dao<T> {
    List<T> readAll();
    void create(T entity);
    T readById(int id);
    void update(T entity);
    void delete(T entity);
}
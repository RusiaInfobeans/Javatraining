package SpringAssesment.dao;

import SpringAssesment.User;

import java.util.List;

public interface UserDao {
    List<User> readAll();

    void create(User user);

    List<Object[]> read();

    List<User> readByEmail(String email);
}
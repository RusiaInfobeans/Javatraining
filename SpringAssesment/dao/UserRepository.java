package SpringAssesment.dao;

import SpringAssesment.User;

public interface UserRepository {
    User get(int id);
    void add(User user);
    void update(User user);
    void remove(User user);
}
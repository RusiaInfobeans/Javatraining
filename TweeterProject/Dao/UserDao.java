package TweeterProject.Dao;

import TweeterProject.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
    User findByEmail(String email);
    void saveUser(User user);


}

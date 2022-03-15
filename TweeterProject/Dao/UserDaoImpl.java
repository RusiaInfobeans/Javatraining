package TweeterProject.Dao;


import TweeterProject.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {
    @Autowired
    Session session;


    @Override
    public List<User> findAll() {
        List<User> resultList = session.createQuery("From User", User.class).getResultList();
        if (!resultList.isEmpty()) return resultList;
        return new ArrayList<>();

    }

    @Override
    public User findByEmail(String email) {

        String SQL = "From User where email = '" + email + "'";
        List<User> resultList = session.createQuery(SQL, User.class).getResultList();
        if (resultList.size() > 0) {
            User singleResult = resultList.get(0);
            return singleResult;
        }
        return new User();
    }


    @Override
    public void saveUser(User user) {
        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();
    }


}


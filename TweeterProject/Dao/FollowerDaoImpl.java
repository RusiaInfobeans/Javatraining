package TweeterProject.Dao;

import TweeterProject.Follower;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FollowerDaoImpl implements FollowerDao {
    @Autowired
    Session session;

    @Override
    public void saveFollower(Follower follower) {
        session.beginTransaction();
        session.persist(follower);
        session.getTransaction().commit();
    }

    @Override
    public List<Follower> findAll(Integer userId) {
        String SQL = "from Follower where user_id = " + userId;
        List<Follower> resultList = session.createQuery(SQL, Follower.class).getResultList();
        if (resultList.size() > 0) {
            return resultList;
        }
        return new ArrayList<>();
    }

}
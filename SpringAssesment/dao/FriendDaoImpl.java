package SpringAssesment.dao;

import SpringAssesment.Friend;
import SpringAssesment.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FriendDaoImpl implements FriendDao {

    @Autowired
    Session session;

    @Override
    public List<Friend> readAll() {
        return session.createQuery("from Friend", Friend.class).getResultList();
    }

    @Override
    public void create(Friend friend) {
        session.beginTransaction();
        session.persist(friend);
        session.getTransaction().commit();
    }

    @Override
    public void createMultiple(int id, Friend friend) {
        User user = session.get(User.class, id);
        session.beginTransaction();
        friend.setUser(user);
        session.persist(friend);
        session.getTransaction().commit();
    }

}
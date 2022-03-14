package SpringAssesment.dao;

import SpringAssesment.Friend;
import SpringAssesment.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;;
import org.hibernate.query.Query;
import java.util.ArrayList;

@Component
public class FriendDaoImpl implements FriendDao {

    @Autowired
    Session session;

    @Override
    public List<Friend> readAll(Integer userId) {
        String SQL = "from Friend where user_id = " + userId;
        List<Friend> resultList = session.createQuery(SQL, Friend.class).getResultList();
        if (resultList.size() > 0) {
            return resultList;
        }
        return new ArrayList<>();
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

    @Override
    public void blockFriend(Integer id) {
        session.beginTransaction();
        String SQL = "UPDATE Friend SET blocked = :true   WHERE id = :id";
        Query query = session.createQuery(SQL);
        query.setParameter("true", true);
        query.setParameter("id", id);
        int i = query.executeUpdate();
        session.getTransaction().commit();
        System.out.println(i + " " + id);
    }

}
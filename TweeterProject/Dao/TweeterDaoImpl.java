package TweeterProject.Dao;

import TweeterProject.Tweet;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class TweeterDaoImpl implements TweeterDao {
    @Autowired
    private Session session;

    @Override
    public List<Tweet> findAllTweets(Integer id) {
        String SQL = "From Tweet where userId =" + id;
        List<Tweet> resultList = session.createQuery(SQL + "", Tweet.class).getResultList();
        return resultList;


    }

    @Override
    public void saveTweet(Tweet tweet) {
        session.beginTransaction();
        session.persist(tweet);
        session.getTransaction().commit();
    }
}
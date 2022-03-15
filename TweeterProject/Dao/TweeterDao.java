package TweeterProject.Dao;

import TweeterProject.Tweet;

import java.util.List;


public interface TweeterDao {
    List<Tweet> findAllTweets(Integer id);

    void saveTweet(Tweet tweet);
}

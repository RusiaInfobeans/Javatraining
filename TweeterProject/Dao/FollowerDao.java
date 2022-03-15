package TweeterProject.Dao;


import TweeterProject.Follower;

import java.util.List;

public interface FollowerDao {
    void saveFollower(Follower follower);

    List<Follower> findAll(Integer userId);
}
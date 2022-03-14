package SpringAssesment.dao;


import SpringAssesment.Friend;

import java.util.List;

public interface FriendDao {
    List<Friend> readAll();

    void create(Friend friend);

    void createMultiple(int id, Friend friend);
}
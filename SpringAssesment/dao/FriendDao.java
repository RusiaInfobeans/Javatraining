package SpringAssesment.dao;

import SpringAssesment.Friend;

import java.util.List;

public interface FriendDao {
    List<Friend> readAll(Integer userid);

    void create(Friend friend);

    void createMultiple(int id, Friend friend);

    void blockFriend(Integer id);
}
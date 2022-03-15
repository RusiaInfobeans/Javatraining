package TweeterProject;

import javax.persistence.*;
@Entity
@Table(name = "follower_spring")
public
class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer followerUserId;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    public Follower(Integer followerUserId, User user) {
        this.followerUserId = followerUserId;
        this.user = user;
    }

    public Follower() {
    }

    public Follower(Integer id, Integer followerUserId, User user) {
        this.id = id;
        this.followerUserId = followerUserId;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Follower{" + "id=" + id + ", followerUserId=" + followerUserId + ", user=" + user + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFollowerUserId() {
        return followerUserId;
    }

    public void setFollowerUserId(Integer followerUserId) {
        this.followerUserId = followerUserId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
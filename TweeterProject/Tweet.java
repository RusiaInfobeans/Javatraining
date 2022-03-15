package TweeterProject;

import javax.persistence.*;
import java.sql.Timestamp;
@Entity
@Table(name ="tweet_spring")
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tweet;
    private Timestamp timestamp;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userId")
    private User user ;

    public Tweet() {
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id=" + id +
                ", tweet='" + tweet + '\'' +
                ", timestamp=" + timestamp +
                ", user=" + user +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tweet(String tweet, Timestamp timestamp, User user) {
        this.tweet = tweet;
        this.timestamp = timestamp;
        this.user = user;
    }
}
package SampleApp.services;

import SampleApp.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gauravmunjal
 * Date: 30/7/12
 * Time: 4:35 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class TweetService {

    private final ThreadLocal<Long> userID;
    public JdbcTemplate db;

    @Autowired
    public TweetService(@Qualifier("userID") ThreadLocal<Long> userID, JdbcTemplate template) {
        this.userID = userID;
        db = template;
    }

    public void updateFeed(int id){
        db.update("INSERT INTO FEED (SELECT followerID, id FROM FOLLOWS, TWEETS where followingID = ? and id=?)",userID.get(),id);
    }

    public int add(Tweet tweet, String username) {
        int id = db.queryForInt("INSERT INTO TWEETS(userID, tweet, username) VALUES (?,?,?) returning ID", userID.get(), tweet.getTweet() ,username);
        updateFeed(id);
        return id;
    }

    public Tweet getTweet(int id) {
        return db.queryForObject("SELECT * FROM TWEETS WHERE ID = ? AND userID = ?", Tweet.rowMapper, id,userID.get());
    }

    public List<Tweet> getFeed() {
        return db.query("SELECT * FROM TWEETS WHERE id IN (SELECT tweetID FROM FEED where userID=?) ORDER BY id DESC",Tweet.rowMapper,userID.get());
    }

    public Integer getLatestTweet(int id){
        return db.queryForInt("SELECT MAX(tempMax.tweetID) AS MAXVAL from (SELECT tweetID FROM FEED WHERE userID=?) AS tempMax",id);
    }

    public List<Tweet> getNewTweets(int latestTweet, int currLatestTweet,int userID) {

        return db.query("select * from tweets where tweets.id in ( select tweetID from FEED where FEED.userID= ? and FEED.tweetID > ? and FEED.tweetID <= ?) order by id asc", Tweet.rowMapper,userID,latestTweet,currLatestTweet);

    }
}

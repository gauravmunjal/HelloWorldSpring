package SampleApp.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: gauravmunjal
 * Date: 25/7/12
 * Time: 5:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class Tweet {

    private int id;
    private int userID;
    private String tweet;
    private String username;
    private Date pushtime;

    public static final RowMapper<Tweet> rowMapper = new RowMapper<Tweet>() {
        @Override public Tweet mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Tweet(resultSet);
        }
    };

    public Tweet() {

    }

    public Tweet(ResultSet rs) throws SQLException {
        id = rs.getInt("id");
        tweet = rs.getString("tweet");
        username = rs.getString("username");
        userID =rs.getInt("userID");
        pushtime = rs.getTimestamp("pushtime");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getPushtime() {
        return pushtime;
    }

    public void setPushtime(Date pushtime) {
        this.pushtime = pushtime;
    }
}

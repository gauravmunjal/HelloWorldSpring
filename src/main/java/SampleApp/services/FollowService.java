package SampleApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: gauravmunjal
 * Date: 30/7/12
 * Time: 5:27 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class FollowService {
    private final ThreadLocal<Long> userID;
    public JdbcTemplate db;

    @Autowired
    public FollowService(@Qualifier("userID") ThreadLocal<Long> userID, JdbcTemplate template) {
        this.userID = userID;
        db = template;
    }

    public boolean isFollowing(int id){
        int n = db.queryForInt("SELECT COUNT(*) FROM FOLLOWS WHERE followerID = ? AND followingID = ?",userID.get(),id);
        if (n==0)
            return false;
        return true;
    }

    public void followUser(int id){
        if(isFollowing(id)){
            unfollowUser(id);
            return;
        }
        db.update("INSERT INTO FOLLOWS VALUES(?,?)",userID.get(), id);
    }

    public void unfollowUser(int id){
        db.update("DELETE FROM FOLLOWS WHERE followerID = ? and followingID = ? ", userID.get(), id);
    }

    public void followOneself(int id) {
        db.update("INSERT INTO FOLLOWS VALUES(?,?)",id, id);
    }
}

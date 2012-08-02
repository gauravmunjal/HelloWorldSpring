package SampleApp.services;

import SampleApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: gauravmunjal
 * Date: 25/7/12
 * Time: 5:18 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserService {
    private final ThreadLocal<Integer> userID;
    public JdbcTemplate db;

    @Autowired
    public UserService(ThreadLocal<Integer> userID, JdbcTemplate db) {
        this.userID = userID;
        this.db = db;
    }

    public int add(User user) {
        return db.queryForInt("INSERT INTO USERS (name, email, username, password) values(?,?,?,?) RETURNING ID",user.getName(),user.getEmail(),user.getUsername(),user.getPassword());

    }

    public User getUser(String vanity) {
        User user = null;
        try {
            user = db.queryForObject("SELECT * FROM USERS where username=? ", User.rowMapper, vanity);
        }
        catch (EmptyResultDataAccessException e) {
            return user;
        }
        return (user);
    }

    public User getProfile() {
        User user = null;
        try {
            user = db.queryForObject("SELECT * FROM USERS where id=? ", User.rowMapper, userID.get());
        }
        catch (EmptyResultDataAccessException e) {
            return user;
        }
        return (user);
    }
}

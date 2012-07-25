package SampleApp.services;

import SampleApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
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
    public JdbcTemplate db;

    @Autowired
    public UserService(JdbcTemplate db) {
        this.db = db;
    }

    public void add(User user) {
        db.update("INSERT INTO USERS (name, email, username, password) values(?,?,?,?)",user.getName(),user.getEmail(),user.getUsername(),user.getPassword());
    }
}

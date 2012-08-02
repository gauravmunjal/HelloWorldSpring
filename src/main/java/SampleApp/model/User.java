package SampleApp.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: gauravmunjal
 * Date: 25/7/12
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class User {

    private int id;
    private String name;
    private String email;
    private String username;
    private String password;

    public static final RowMapper<User> rowMapper = new RowMapper<User>() {
        @Override public User mapRow(ResultSet resultSet, int i) throws SQLException {
            return new User(resultSet);
        }
    };

    public User() {

    }

    public User(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt("id");
        name = resultSet.getString("name");
        email = resultSet.getString("email");
        username = resultSet.getString("username");
        password = resultSet.getString("password");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}

package SampleApp.model;

/**
 * Created with IntelliJ IDEA.
 * User: gauravmunjal
 * Date: 25/7/12
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class User {
    private String name;
    private String email;
    private String username;

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

    private String password;


}

package SampleApp;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class AppConfig {
    @Bean
    public JdbcTemplate simpleJdbcTemplate() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://127.0.0.1:5432/mydb1");
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("crossword");
        JdbcTemplate db = new JdbcTemplate(dataSource);
        try {
            int a =  db.queryForInt("SELECT COUNT(*) FROM USERS;");
        }
        catch (Exception e) {
            //TODO:Remove Sysout
            System.out.print(e.toString());
            db.update("CREATE TABLE USERS (" +
                        "id SERIAL," +
                        "PRIMARY KEY(id)," +
                        "name varchar(30) NOT NULL," +
                        "email varchar(30) NOT NULL," +
                        "username varchar(30) NOT NULL," +
                        "password varchar(30) NOT NULL" +
                        ");");
        }
        try {
            int a =  db.queryForInt("SELECT COUNT(*) FROM TWEETS;");
        }
        catch (Exception e) {
            //TODO:Remove Sysout
            System.out.print(e.toString());
            db.update("CREATE TABLE TWEETS (" +
                        "id SERIAL," +
                        "PRIMARY KEY(id)," +
                        "userID INTEGER," +
                        "tweet varchar(30) NOT NULL," +
                        "username varchar(30) NOT NULL," +
                        "pushtime  timestamp DEFAULT current_timestamp" +
                        ");");
        }

        try {
            int a =  db.queryForInt("SELECT COUNT(*) FROM FOLLOWS;");
        }
        catch (Exception e) {
            //TODO:Remove Sysout
            System.out.print(e.toString());
            db.update("CREATE TABLE FOLLOWS (" +
                        "followerID INTEGER," +
                        "followingID INTEGER" +
                        ");");
        }

        try {
            int a =  db.queryForInt("SELECT COUNT(*) FROM FEED;");
        }
        catch (Exception e) {
            //TODO:Remove Sysout
            System.out.print(e.toString());
            db.update("CREATE TABLE FEED (" +
                        "userID INTEGER," +
                        "tweetID INTEGER" +
                        ");");
        }
        return db;
    }

    @Bean
    public ThreadLocal<Integer> userID() {
        return new ThreadLocal<Integer>();
    }
}

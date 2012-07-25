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
        db.update("DROP TABLE USERS;");
        db.update("CREATE TABLE USERS (" +
                    "id SERIAL," +
                    "PRIMARY KEY(id)," +
                    "name varchar(30) NOT NULL," +
                    "email varchar(30) NOT NULL," +
                    "username varchar(30) NOT NULL," +
                    "password varchar(30) NOT NULL" +
                    ");");
        return db;
    }
}

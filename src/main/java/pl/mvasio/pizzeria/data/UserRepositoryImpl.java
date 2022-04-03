package pl.mvasio.pizzeria.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import pl.mvasio.pizzeria.Ingredient;
import pl.mvasio.pizzeria.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Repository
@Slf4j
public class UserRepositoryImpl implements UserRepository{

    private JdbcTemplate jdbc;
    private SimpleJdbcInsert userInserter;
    private ObjectMapper objectMapper;

    @Autowired
    public UserRepositoryImpl (JdbcTemplate jdbc){
        this.jdbc = jdbc;
        this.userInserter = new SimpleJdbcInsert(jdbc).withTableName("USERS");
        this.objectMapper = new ObjectMapper();

    }

    @Override
    public void addUser(User user) {
        @SuppressWarnings("unchecked")
        Map<String, Object> values = objectMapper.convertValue(user, Map.class);
        userInserter.execute(values);
        jdbc.update("insert into authorities values (?, 'ROLE_USER')", user.getUsername());
    }

    @Override
    public User getUser(String username) {
        return jdbc.queryForObject("SELECT * FROM USER WHERE USERNAME = ?", this::mapRowToUser, username);
    }

    @Override
    public User updateUser(String username) {
        return null;
    }

    @Override
    public User removeUser(String username) {
        return null;
    }

    private User mapRowToUser (ResultSet rs, int row) throws SQLException {
        return objectMapper.convertValue(rs, User.class);
    }
}

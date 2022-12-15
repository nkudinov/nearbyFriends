package com.example.nearbyfriends.repository;

import com.example.nearbyfriends.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class UserRepository {
    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final JdbcTemplate jdbcTemplate;

    private User mapRowToUser(ResultSet rs, int rowNum) throws SQLException {
        LocalDate birthday = null;
        if (rs.getDate("birthday") != null) {
            birthday = rs.getDate("birthday").toLocalDate();
        }
        return new User(rs.getInt("user_id"), rs.getString("user_name"), birthday);
    }

    public User save(User user) {
        log.info("create user: {}", user);
        final String sql = "INSERT INTO users(user_id, user_name, birthday)  VALUES(?,?,?)";
        jdbcTemplate.update(sql, user.userId, user.userName, user.birthdate);
        log.info("created");
        return user;
    }

    public Optional<User> findById(Integer id) {
        String sql = "SELECT * FROM users WHERE user_id= ?";
        List<User> users = jdbcTemplate.query(sql, this::mapRowToUser, id);
        log.info("found :{}", users);
        if (users.size() > 1) {
            throw new IllegalStateException(" There must be not more than 1 row in response:" + users);
        }
        return Optional.of(users.get(0));
    }

    public boolean existsById(Integer id) {
        return findById(id).isPresent();
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM users ";
        return jdbcTemplate.query(sql, this::mapRowToUser);
    }


}

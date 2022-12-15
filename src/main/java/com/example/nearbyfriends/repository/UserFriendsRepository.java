package com.example.nearbyfriends.repository;

import com.example.nearbyfriends.domain.User;
import com.example.nearbyfriends.domain.UserFriend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class UserFriendsRepository {
    @Autowired
    public UserFriendsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final JdbcTemplate jdbcTemplate;

    private UserFriend mapRowToUserFriends(ResultSet rs, int rowNum) throws SQLException {
        return new UserFriend(rs.getInt("user_id"), rs.getInt("fried_id"));
    }
    public void save(UserFriend userFriend) {
        log.info("create user friend: {}", userFriend);
        final String sql = "INSERT INTO user_friends(user_id, friend_id)  VALUES(?,?)";
        jdbcTemplate.update(sql, userFriend.userId(), userFriend.friendId());
        log.info("created");
    }

    public List<UserFriend> findById(Integer userId) {
        String sql = "SELECT * FROM user_friends WHERE user_id= ?";
        List<UserFriend> userFriends = jdbcTemplate.query(sql, this::mapRowToUserFriends, userId);
        log.info("found :{}", userFriends);
        return userFriends;
    }
}

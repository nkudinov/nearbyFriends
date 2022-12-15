package com.example.nearbyfriends.repository;

import com.example.nearbyfriends.domain.UserLocation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class UserLocationRepository {

    public UserLocationRepository(RedisTemplate<Integer, UserLocation> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private RedisTemplate<Integer, UserLocation> redisTemplate;

    public void save(UserLocation userLocation) {
        log.info("location: {}", userLocation);
        redisTemplate.opsForValue().set(userLocation.userId, userLocation);
    }

    public UserLocation findById(int userId) {
        log.info("try find by: {}", userId);
        UserLocation userLocation = redisTemplate.opsForValue().get(userId);
        log.info("found: {}", userLocation);
        return userLocation;
    }

    public void update(UserLocation userLocation) {
        redisTemplate.opsForValue().set(userLocation.userId, userLocation);
    }

    public void deleteById(int userId) {
        redisTemplate.opsForValue().getOperations().delete(userId);
    }
}

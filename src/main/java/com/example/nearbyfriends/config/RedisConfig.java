package com.example.nearbyfriends.config;

import com.example.nearbyfriends.domain.UserLocation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName("redis-svc");
        return jedisConnectionFactory;

    }

    @Bean
    public RedisTemplate<Integer, UserLocation> redisTemplate() {
        RedisTemplate<Integer, UserLocation> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

}

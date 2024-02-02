package com.example.demo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisTemplateConfig {
    @Bean(name = "alarmRedisTemplate")
    @Primary
    public RedisTemplate<String, Object> alarmRedisTemplate(@Qualifier("alarmRedisConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean(name = "chatRedisTemplate")
    public RedisTemplate<String, Object> chatRedisTemplate(@Qualifier("chatRedisConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}
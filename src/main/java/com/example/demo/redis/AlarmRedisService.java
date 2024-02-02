package com.example.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class AlarmRedisService {

    @Autowired
    @Qualifier("alarmRedisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
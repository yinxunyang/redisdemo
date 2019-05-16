package com.redisdemo;

import com.redisdemo.config.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Component;

@Component
public class RedisMain {
    @Autowired
    private RedisConfig redisConfig;
    /*public void useRedisCallback() {
        redisConfig.initRedisTemplate().execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.set("key7".getBytes(), "value7".getBytes());
                redisConnection.set("key8".getBytes(), "value8".getBytes());
                return null;
            }
        });
    }*/

    public void useSessionCallback() {
        redisConfig.initRedisTemplate().execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                redisOperations.opsForValue().set("key9", "value9");
                redisOperations.opsForValue().set("key10", "value10");
                return null;
            }
        });
    }
}

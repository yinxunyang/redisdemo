package com.redisdemo.service.impl;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    @CachePut(value = "redisCache", key = "#result")
    public String insertUser(String name) {
        System.out.println("name:" + name);
        return name;
    }

    @Override
    @Cacheable(value = "redisCache", key = "#result")
    public String getUser(String result) {
        System.out.println("name:" + result);
        return result;
    }
}

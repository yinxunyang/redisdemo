package com.redisdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisdemoApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisMain redisMain;

    @Test
    public void test() {

        // 保存字符串
        stringRedisTemplate.opsForValue().set("aaa", "111");

    }

    @Test
    public void test1(){
        redisMain.useSessionCallback();
    }




}

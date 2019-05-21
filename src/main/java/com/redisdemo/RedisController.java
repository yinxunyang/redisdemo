package com.redisdemo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @RequestMapping("/testwatch")
    @ResponseBody
    public void testwatch() {
        redisTemplate.opsForValue().set("k1", "v1");
        List list = (List) redisTemplate.execute(
                (RedisOperations redisop) -> {
                    // 设置要监控的key值
                    redisop.watch("k1");
                    // 开启事务，在exec执行前，全部都只是进入队列
                    redisop.multi();
                    redisop.opsForValue().set("k2", "v2");
                    // 因为k1是字符串，对字符串加一，显然是不能进行的
                    redisop.opsForValue().increment("k1", 1);
                    // 获取值将为空，因为redis只是把命令放入队列
                    Object value = redisop.opsForValue().get("k2");
                    return redisop.exec();
                }
        );
        System.out.println(list);
    }
}

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


    @RequestMapping("/testpipeline")
    @ResponseBody
    public void testwatch() {
        Long start = System.currentTimeMillis();
        List list = (List) redisTemplate.executePipelined(
                (RedisOperations redisop) -> {
                    for (int i = 0; i <= 100000; i++) {
                        redisop.opsForValue().set("pipe_test" + i, "v_" + i);
                    }
                   /* for (int i = 0; i <= 100000; i++) {
                        redisop.delete("pipe_test" + i);
                    }*/
                    return null;
                }
        );
        Long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "ms");
    }
}

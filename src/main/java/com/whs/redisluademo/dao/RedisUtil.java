package com.whs.redisluademo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Component
public class RedisUtil {

    private DefaultRedisScript<Long> redisScript;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostConstruct
    public void init(){
        redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Long.class);
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis/save_token.lua")));
    }

    public Object saveToken(String key, String token,  double score, int expires, int size, long currentTime) {
//        public Object save(){
//        Number number = redisTemplate.execute(redisScript, keys, rateLimit.count(), rateLimit.time());

        Long number = (Long) redisTemplate.execute(redisScript, Collections.singletonList(key), token, score, expires, size, currentTime);

        if (number != null && number.intValue() != 0 && number.intValue() <= size) {
            System.out.println("保存成功。。。。");
        }else {
            System.out.println("保存失败。。。");
        }
        return number;

    }


}

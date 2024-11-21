package com.whs.redisluademo.controller;


import com.whs.redisluademo.dao.RedisUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class TokenController {


    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/save")
    public String savetoken(){

        String key = "token:save";
        String token = RandomStringUtils.randomAlphanumeric(10);

        Random rd = new Random();
        long score = rd.nextInt(10000);

        int expire = 30;
        int size = 5;

        long currentTime = 100000;
        redisUtil.saveToken(key,token,score,expire,size,currentTime);

        return null;
    }

}

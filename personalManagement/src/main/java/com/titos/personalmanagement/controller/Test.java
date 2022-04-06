package com.titos.personalmanagement.controller;

import com.titos.rpc.redis.RedisRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class Test {
    @Resource
    private RedisRpc redisRpc;
    @GetMapping("/hello")
    public String sayHello() {
        redisRpc.get("hello");
        return "你好";
    }
}

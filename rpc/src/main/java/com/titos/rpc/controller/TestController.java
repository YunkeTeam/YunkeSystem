package com.titos.rpc.controller;

import com.titos.info.redis.vo.RedisVO;
import com.titos.rpc.redis.RedisRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/4/4 17:30
 **/
@RestController
public class TestController {

    @Autowired
    private RedisRpc redisRpc;

    /**
     * 将键值对存入redis
     * @param redisVO 键值对和过期时间
     * @return null
     */
    @PostMapping(value = "/set")
    public String set(@RequestBody RedisVO redisVO) {
        redisRpc.set(redisVO);
        return null;
    }

    /**
     * 从redis中根据键获取值
     *
     * @param key 键
     * @return 值
     */
    @GetMapping(value = "/get")
    public Object get(@RequestParam(required = true) String key) {
        return redisRpc.get(key);
    }

    /**
     * 根据key从redis删除键值对
     *
     * @param key 键
     * @return 是否删除成功
     */
    @PostMapping(value = "/delete")
    public Boolean delete(@RequestParam(required = true) String key) {
        return redisRpc.delete(key);
    }

    /**
     * 根据key从redis批量删除键值对
     *
     * @param keys 键值对列表
     * @return Long
     */
    @PostMapping(value = "/delete/list")
    public Long delete(@RequestParam(required = true) List<String> keys) {
        return redisRpc.delete(keys);
    }

    /**
     * 根据key设置键值对的过期时间
     *
     * @param key  键
     * @param time 过期时间
     * @return 是否设置成功
     */
    @PostMapping(value = "/setexpire")
    public Boolean expire(
            @RequestParam(required = true) String key,
            @RequestParam(required = true) Long time) {
        return redisRpc.expire(key, time);
    }

    /**
     * 根据key获取键值对的过期时间
     *
     * @param key 键
     * @return 过期时间
     */
    @GetMapping(value = "/getexpire")
    public Long getExpire(@RequestParam(required = true) String key) {
        return redisRpc.getExpire(key);
    }

    /**
     * 判断是否有某个key
     *
     * @param key 查询的key
     * @return 是否有这个key
     */
    @GetMapping(value = "/haskey")
    public Boolean hasKey(@RequestParam(required = true) String key) {
        return redisRpc.hasKey(key);
    }


}

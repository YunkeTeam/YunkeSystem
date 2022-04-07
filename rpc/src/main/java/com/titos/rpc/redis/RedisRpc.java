package com.titos.rpc.redis;

import com.titos.info.redis.vo.RedisVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName RedisRpc
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/4/4 17:23
 **/
@FeignClient("COMMON")
public interface RedisRpc {

    /**
     * 将键值对存入redis
     * @param redisVO 键值对和过期时间
     * @return null
     */
    @PostMapping(value = "/redis/set")
    String set(@RequestBody RedisVO redisVO);

    /**
     * 从redis中根据键获取值
     *
     * @param key 键
     * @return 值
     */
    @GetMapping(value = "/redis/get")
    Object get(@RequestParam(required = true) String key);

    /**
     * 根据key从redis删除键值对
     *
     * @param key 键
     * @return 是否删除成功
     */
    @PostMapping(value = "/redis/delete")
    Boolean delete(@RequestParam(required = true) String key);

    /**
     * 根据key从redis批量删除键值对
     *
     * @param keys 键值对列表
     * @return Long
     */
    @PostMapping(value = "/redis/delete/list")
    Long delete(@RequestParam(required = true) List<String> keys);

    /**
     * 根据key设置键值对的过期时间
     *
     * @param key  键
     * @param time 过期时间
     * @return 是否设置成功
     */
    @PostMapping(value = "/redis/setexpire")
    Boolean expire(
            @RequestParam(required = true) String key,
            @RequestParam(required = true) Long time);

    /**
     * 根据key获取键值对的过期时间
     *
     * @param key 键
     * @return 过期时间
     */
    @GetMapping(value = "/redis/getexpire")
    Long getExpire(@RequestParam(required = true) String key);

    /**
     * 判断是否有某个key
     *
     * @param key 查询的key
     * @return 是否有这个key
     */
    @GetMapping(value = "/redis/haskey")
    Boolean hasKey(@RequestParam(required = true) String key);

}



package com.titos.info.redis.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName RedisVO
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/4/5 12:14
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RedisVO implements Serializable {

    private String key;

    private Object value;

    private Long time;

}


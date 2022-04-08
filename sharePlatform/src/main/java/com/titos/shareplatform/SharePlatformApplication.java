package com.titos.shareplatform;

import com.titos.tool.annotions.EnableYunKeAutoUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName SharePlatformApplication
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/3/30 22:20
 **/
@MapperScan("com.titos.shareplatform.dao")
@EnableFeignClients("com.titos.rpc.redis")
@EnableDiscoveryClient
@EnableYunKeAutoUtils
@SpringBootApplication
public class SharePlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(SharePlatformApplication.class, args);
    }
}

package com.titos.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName CommonApplication
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/4/4 12:00
 **/
@EnableFeignClients // 开启feign
@EnableDiscoveryClient // 开启nacos服务注册与发现
@SpringBootApplication
public class CommonApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class, args);
    }
}

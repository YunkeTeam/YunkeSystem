package com.titos.shareplatform;

import com.titos.tool.annotions.EnableYunKeAutoUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName SharePlatformApplication
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/3/30 22:20
 **/
@EnableDiscoveryClient
@EnableYunKeAutoUtils
@SpringBootApplication
public class SharePlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(SharePlatformApplication.class, args);
    }
}

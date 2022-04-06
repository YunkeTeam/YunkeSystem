package com.titos.personalmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Titos
 */
@EnableFeignClients // 开启feign
@EnableDiscoveryClient // 开启nacos服务注册与发现
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class PersonalManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalManagementApplication.class, args);
    }

}

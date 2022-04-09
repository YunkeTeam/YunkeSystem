package com.titos.technicalarchive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName TechnicalArchiveApplication
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/4/8 14:32
 **/
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class TechnicalArchiveApplication {
    public static void main(String[] args) {
        SpringApplication.run(TechnicalArchiveApplication.class, args);
    }
}

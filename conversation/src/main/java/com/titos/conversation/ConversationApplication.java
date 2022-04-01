package com.titos.conversation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName ConversationApplication
 * @Description TODO
 * @Author Kurihada
 * @Date 2022/3/30 21:44
 **/
@SpringBootApplication
public class ConversationApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConversationApplication.class, args);
    }
}

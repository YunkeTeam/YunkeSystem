package com.titos.conversation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ddgo
 */
@SpringBootApplication
@MapperScan("com.titos.conversation.dao")
public class ConversationApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConversationApplication.class, args);
    }
}

package com.titos.personalmanagement.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class TestController {
    @Value("${YK.token.secretKey}")
    private String username;
    @GetMapping("/username")
    public String now() {
        return username;
    }
}

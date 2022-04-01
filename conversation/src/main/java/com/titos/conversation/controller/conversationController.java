package com.titos.conversation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: ddgo
 * @DateTime: 2022/4/1 20:47
 * @Version: 1.0.0
 * @Description: TODO
 */
@Controller
@RequestMapping("/conversation")
public class conversationController {

    @GetMapping("/startChat")
    public ModelAndView startChat(String token, String uid) {

        return null;
    }

}

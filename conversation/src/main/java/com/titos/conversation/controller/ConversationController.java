package com.titos.conversation.controller;

import com.sun.deploy.net.HttpResponse;
import com.titos.conversation.po.MessagePO;
import com.titos.conversation.service.impl.ConversationServiceImpl;
import com.titos.info.global.CommonResult;
import com.titos.info.global.enums.StatusEnum;
import com.titos.tool.token.TokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: ddgo
 * @DateTime: 2022/4/1 20:47
 * @Version: 1.0.0
 * @Description: TODO
 */
@Controller
@RequestMapping("/conversation")
public class ConversationController {

    @Autowired
    ConversationServiceImpl service;

    /**
     * 用户点击交流框，会返回与被点击头像私信用户的所有聊天信息。
     * @param model model
     * @param token 传入token
     * @param id 点击用户的 id
     */
    @GetMapping("/clickBox")
    public void startDialog(ModelMap model, String token, String id) {
        CommonResult<List<MessagePO>> commonResult = null;
        try {
            Integer userId = TokenUtil.verifyTokenAndGetUserId(token);
            Integer otherUserId = Integer.parseInt(id);
            commonResult = service.selectAllDialog(userId, otherUserId);
        }catch (ExpiredJwtException e) {
            commonResult = new CommonResult<>(false, StatusEnum.TOKEN_ERROR.getCode(), StatusEnum.TOKEN_ERROR.getMsg(), null);
        }
        model.addAttribute("commonResult", commonResult);
    }


    /**
     * 删除两个用户交流的所有信息
     * @param model model
     * @param token 传入token
     * @param id 被删除的用户的 id
     */
    @DeleteMapping("/deleteDialog")
    public void deleteDialog(ModelMap model, String token, String id) {
        CommonResult<Boolean> commonResult = null;
        try {
            Integer userId = TokenUtil.verifyTokenAndGetUserId(token);
            Integer otherUserId = Integer.parseInt(id);
            commonResult = service.deleteDialog(userId, otherUserId);
        }catch (ExpiredJwtException e) {
            commonResult = new CommonResult<>(false, StatusEnum.TOKEN_ERROR.getCode(), StatusEnum.TOKEN_ERROR.getMsg(), null);
        }
        model.addAttribute("commonResult", commonResult);
    }

    /**
     * 发送聊天信息
     * @param model model
     * @param token 传入token
     * @param id 接收者的id
     * @param message 传输的信息
     */
    @PostMapping("/sendMessage")
    public void sendMessage(ModelMap model, String token, String id, String message) {
        CommonResult<Boolean> commonResult = null;
        try {
            Integer userId = TokenUtil.verifyTokenAndGetUserId(token);
            Integer otherUserId = Integer.parseInt(id);
            commonResult = service.sendDialog(userId, otherUserId,message);
        }catch (ExpiredJwtException e) {
            commonResult = new CommonResult<>(false, StatusEnum.TOKEN_ERROR.getCode(), StatusEnum.TOKEN_ERROR.getMsg(), null);
        }
        model.addAttribute("commonResult", commonResult);
    }

    /**
     * 添加好友
     * @param model model
     * @param token 传入token
     * @param id 被加好友的id
     */
    @PostMapping("/addFriend")
    public void addFriend(ModelMap model, String token, String id) {
        CommonResult<Boolean> commonResult = null;
        try {
            Integer userId = TokenUtil.verifyTokenAndGetUserId(token);
            Integer otherUserId = Integer.parseInt(id);
            commonResult = service.addFriend(userId, otherUserId);
        }catch (ExpiredJwtException e) {
            commonResult = new CommonResult<>(false, StatusEnum.TOKEN_ERROR.getCode(), StatusEnum.TOKEN_ERROR.getMsg(), null);
        }
        model.addAttribute("commonResult", commonResult);
    }

}

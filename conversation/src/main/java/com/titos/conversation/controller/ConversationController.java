package com.titos.conversation.controller;

import com.titos.conversation.po.MessagePO;
import com.titos.conversation.service.ConversationService;
import com.titos.conversation.vo.SimpleInformationVO;
import com.titos.info.global.CommonResult;
import com.titos.info.global.enums.StatusEnum;
import com.titos.tool.annotions.InjectToken;
import com.titos.tool.exception.ParameterException;
import com.titos.tool.token.CustomStatement;
import com.titos.tool.token.TokenUtil;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @Author: ddgo
 * @DateTime: 2022/4/1 20:47
 * @Version: 1.0.0
 * @Description: TODO
 */
@RestController
@RequestMapping("/conversation")
public class ConversationController {

    @Autowired
    ConversationService service;

//    /**
//     * 返回与被点击头像私信用户的所有聊天信息。
//     * @param customStatement 注解解析的对象信息
//     * @param toId 被点击用户的 toId
//     * TODO：没有实现分页，redis
//     */
//    @InjectToken
//    @PostMapping("/getAllMessage")
//    public CommonResult<List<MessagePO>> getAllMessage(CustomStatement customStatement, String toId) {
//        CommonResult<List<MessagePO>> commonResult = null;
//        try {
//            Integer userId = customStatement.getId();
//            Integer otherUserId = Integer.parseInt(toId);
//            List<MessagePO> result = service.selectAllDialog(userId, otherUserId);
//            commonResult = CommonResult.success(result, StatusEnum.SUCCESS.getMsg());
//        } catch (ParameterException e) {
//            commonResult = CommonResult.fail(StatusEnum.TOKEN_ERROR.getCode(), StatusEnum.TOKEN_ERROR.getMsg());
//        }
//        return commonResult;
//    }
//
//    /**
//     * 删除两个用户交流的所有信息
//     * @param customStatement 注解解析的对象信息
//     * @param toId 被删除的用户的 id
//     */
//    @InjectToken
//    @DeleteMapping("/deleteDialog")
//    public CommonResult<Boolean> deleteDialog(CustomStatement customStatement, String toId) {
//        CommonResult<Boolean> commonResult = null;
//        try {
//            Integer userId = customStatement.getId();
//            Integer otherUserId = Integer.parseInt(toId);
//            service.deleteDialog(userId, otherUserId);
//            commonResult = CommonResult.success(true, StatusEnum.SUCCESS.getMsg());
//        } catch (ParameterException e) {
//            commonResult = CommonResult.fail(StatusEnum.TOKEN_ERROR.getCode(), StatusEnum.TOKEN_ERROR.getMsg());
//        }
//        return commonResult;
//    }
//
//    /**
//     * 添加好友
//     * @param customStatement 传入token
//     * @param toId 被加好友的id
//     */
//    @InjectToken
//    @PostMapping("/addFriend")
//    public CommonResult<Boolean> addFriend(CustomStatement customStatement, String toId) {
//        CommonResult<Boolean> commonResult = null;
//        try {
//            Integer userId = customStatement.getId();
//            Integer otherUserId = Integer.parseInt(toId);
//            service.addFriend(userId, otherUserId);
//            commonResult = CommonResult.success(true, StatusEnum.SUCCESS.getMsg());
//        } catch (ParameterException e) {
//            commonResult = CommonResult.fail(StatusEnum.TOKEN_ERROR.getCode(), StatusEnum.TOKEN_ERROR.getMsg());
//        }
//        return commonResult;
//    }
    /**
     * 返回与被点击头像私信用户的所有聊天信息。
     * @param toId 被点击用户的 toId
     * TODO：没有实现分页，redis
     */
    @PostMapping("/getAllMessage")
    public CommonResult<List<MessagePO>> getAllMessage(String token, String toId) {
        CustomStatement customStatement = TokenUtil.getMsgFromToken(token, "YUNKE");
        CommonResult<List<MessagePO>> commonResult = null;
        try {
            Integer userId = customStatement.getId();
            Integer otherUserId = Integer.parseInt(toId);
            List<MessagePO> result = service.selectAllDialog(userId, otherUserId);
            commonResult = CommonResult.success(result, StatusEnum.SUCCESS.getMsg());
        } catch (ParameterException e) {
            commonResult = CommonResult.fail(StatusEnum.TOKEN_ERROR.getCode(), StatusEnum.TOKEN_ERROR.getMsg());
        }
        return commonResult;
    }

    /**
     * 删除两个用户交流的所有信息
     * @param toId 被删除的用户的 id
     */
    @DeleteMapping("/deleteDialog")
    public CommonResult<Boolean> deleteDialog(String token, String toId) {
        CustomStatement customStatement = TokenUtil.getMsgFromToken(token, "YUNKE");
        CommonResult<Boolean> commonResult = null;
        try {
            Integer userId = customStatement.getId();
            Integer otherUserId = Integer.parseInt(toId);
            service.deleteDialog(userId, otherUserId);
            commonResult = CommonResult.success(true, StatusEnum.SUCCESS.getMsg());
        } catch (ParameterException e) {
            commonResult = CommonResult.fail(StatusEnum.TOKEN_ERROR.getCode(), StatusEnum.TOKEN_ERROR.getMsg());
        }
        return commonResult;
    }

    /**
     * 添加好友
     * @param toId 被加好友的id
     */
    @PostMapping("/addFriend")
    public CommonResult<Boolean> addFriend(String token, String toId) {
        CustomStatement customStatement = TokenUtil.getMsgFromToken(token, "YUNKE");
        CommonResult<Boolean> commonResult = null;
        try {
            Integer userId = customStatement.getId();
            Integer otherUserId = Integer.parseInt(toId);
            service.addFriend(userId, otherUserId);
            commonResult = CommonResult.success(true, StatusEnum.SUCCESS.getMsg());
        } catch (ParameterException e) {
            commonResult = CommonResult.fail(StatusEnum.TOKEN_ERROR.getCode(), StatusEnum.TOKEN_ERROR.getMsg());
        }
        return commonResult;
    }

    /**
     * 得到当前用户的所有好友的聊天简况
     * @param token
     * @return
     */
    @GetMapping("/getSimpleInformation")
    public CommonResult<SimpleInformationVO> getSimpleInformationVO(String token) {
        CustomStatement customStatement = TokenUtil.getMsgFromToken(token, "YUNKE");
        SimpleInformationVO simpleInformationVO = null;
        try {

        }catch (DataAccessException e) {
            return CommonResult.fail(simpleInformationVO);
        }
        return CommonResult.success(simpleInformationVO);
    }


}

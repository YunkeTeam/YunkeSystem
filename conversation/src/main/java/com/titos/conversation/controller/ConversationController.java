package com.titos.conversation.controller;

import com.titos.conversation.po.MessagePO;
import com.titos.conversation.service.ConversationService;
import com.titos.conversation.vo.SimpleInformationVO;
import com.titos.info.global.CommonResult;
import com.titos.info.global.enums.StatusEnum;
import com.titos.tool.exception.ParameterException;
import com.titos.tool.token.CustomStatement;
import com.titos.tool.token.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
     * 返回与被点击头像私信用户的所有历史聊天信息。
     * @param toId 被点击用户的 toId
     * TODO：没有实现分页，redis
     */
    @PostMapping("/getAllMessage")
    public CommonResult<List<MessagePO>> getAllMessage(String token, String toId) {
        CustomStatement customStatement = TokenUtil.getMsgFromToken(token, "YUNKE");
        Integer userId = customStatement.getId();
        Integer otherUserId = Integer.parseInt(toId);
        List<MessagePO> result = service.selectAllDialog(userId, otherUserId);
        if(result == null) {
            return CommonResult.fail(null);
        }
        return CommonResult.success(result);
    }

    /**
     * 删除两个用户交流的所有信息
     * @param toId 被删除的用户的 id
     */
    @DeleteMapping("/deleteDialog")
    public CommonResult<Boolean> deleteDialog(String token, String toId) {
        CustomStatement customStatement = TokenUtil.getMsgFromToken(token, "YUNKE");
        CommonResult<Boolean> commonResult = null;
        Integer userId = customStatement.getId();
        Integer otherUserId = Integer.parseInt(toId);
        int cnt = service.deleteDialog(userId, otherUserId);
        if(cnt == -1) {
            return CommonResult.fail(false);
        }
        return CommonResult.success(true);
    }

    /**
     * 添加好友
     * @param toId 被加好友的id
     */
    @PostMapping("/addFriend")
    public CommonResult<Boolean> addFriend(String token, String toId) {
        CustomStatement customStatement = TokenUtil.getMsgFromToken(token, "YUNKE");
        CommonResult<Boolean> commonResult = null;
        Integer userId = customStatement.getId();
        Integer otherUserId = Integer.parseInt(toId);
        int cnt = service.addFriend(userId, otherUserId);
        if(cnt == -1) {
            return CommonResult.fail(false);
        }
        return CommonResult.success(true);
    }

    /**
     * 得到当前用户的所有好友的聊天简况
     * @param token
     * @return
     */
    @PostMapping("/getSimpleInformation")
    public CommonResult<List<SimpleInformationVO>> getSimpleInformationVO(String token) {
        CustomStatement customStatement = TokenUtil.getMsgFromToken(token, "YUNKE");
        List<SimpleInformationVO> simpleInformationVO = service.getSimpleInformation(customStatement.getId());
        if(simpleInformationVO == null) {
            return CommonResult.fail(null);
        }
        return CommonResult.success(simpleInformationVO);
    }

    /**
     * 获取toId 在id 离线时发送的信息
     * @param id
     * @param toId
     * @return
     */
    @PostMapping("/getAllDialogReceiveNotComplete")
    public CommonResult<List<MessagePO>> getAllDialogReceiveNotComplete(Integer id, Integer toId) {
        // 从数据库里面拿看是否有 toId 向 id 发送过离线消息，按时间顺序从小到大
        List<MessagePO> messagePOList = service.selectAllDialogReceiveNotComplete(toId, id);
        if(messagePOList == null) {
            return CommonResult.fail(null);
        }
        return CommonResult.success(messagePOList);
    }

}

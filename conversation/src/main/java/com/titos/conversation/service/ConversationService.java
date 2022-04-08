package com.titos.conversation.service;

import com.titos.conversation.po.MessagePO;
import com.titos.info.global.CommonResult;

import java.util.List;

/**
 * @Author: ddgo
 * @DateTime: 2022/4/1 20:47
 * @Version: 1.0.0
 * @Description:
 */

public interface ConversationService {

    /**
     * 先从 redis 里面获取，再去数据库里面查询
     * 查询两个人的所有对话信息
     * @param id 发起者的id
     * @param otherId 接收者的id
     * @return 返回两个人的所有对话信息
     */
    List<MessagePO> selectAllDialog(Integer id, Integer otherId);

    /**
     * 删除对话
     * @param id 发起者id
     * @param otherId 被删除者id
     * @return 返回删除情况
     */
    int deleteDialog(Integer id, Integer otherId);

    /**
     * 发送信息
     * @param id 发送者id
     * @param otherId 接收者id
     * @param message 发送的消息
     * @param isComplete 是否成功发送
     * @return 返回接收情况
     */
    int sendDialog(Integer id, Integer otherId, String message, Integer isComplete);

    /**
     * 添加好友
     * @param id 添加者
     * @param otherId 被添加者
     * @return 返回是否添加成功
     */
    int addFriend(Integer id, Integer otherId);

    /**
     * 先从 redis 里面获取，再去数据库里面查询
     * 查询两个人的所有对话信息中，我未接收成功的信息
     * @param id 发起者的id
     * @param otherId 接收者的id
     * @return 返回两个人的所有对话信息
     */
    List<MessagePO> selectAllDialogReceiveNotComplete(Integer id, Integer otherId);
}

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
    CommonResult<List<MessagePO>> selectAllDialog(Integer id, Integer otherId);
}

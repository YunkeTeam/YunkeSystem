package com.titos.conversation.dao;

import com.titos.conversation.po.MessagePO;

import java.util.List;

/**
 * @Author: ddgo
 * @DateTime: 2022/4/2 23:44
 * @Version: 1.0.0
 * @Description:
 */
public interface ConversationDao {
    /**
     * 查询两个人的对话
     * @param id 发送者的id
     * @param otherId 接收者的id
     * @return 返回所有message表中两人的记录
     */
    List<MessagePO> selectAllDialog(Integer id, Integer otherId);
}

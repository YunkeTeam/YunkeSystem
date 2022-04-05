package com.titos.conversation.dao;

import com.titos.conversation.po.MessagePO;
import org.springframework.dao.DataAccessException;

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
     * @throws DataAccessException 数据访问错误
     */
    List<MessagePO> selectAllDialog(Integer id, Integer otherId) throws DataAccessException;

    /**
     * 删除两个人的所有聊天信息和好友关系信息
     * @param id 发起者id
     * @param otherId 被删除者id
     * @return 返回删除的个数
     * @throws DataAccessException 数据访问错误
     */
    int deleteDialog(Integer id, Integer otherId) throws DataAccessException;

    /**
     * 插入聊天信息
     * @param id 发起者id
     * @param otherId 接收者id
     * @param message 传输信息
     * @return 返回插入条数
     * @throws DataAccessException 数据访问错误
     */
    int insertDialog(Integer id, Integer otherId, String message) throws DataAccessException;

    /**
     * 建立两个好友之间的关系
     * @param id 发起者id
     * @param otherId 接受者id
     * @return 返回插入结果
     * @throws DataAccessException 数据访问错误
     */
    int insertFriend(Integer id, Integer otherId) throws DataAccessException;

    /**
     * 删除两个好友之间的联系
     * @param id 删除者id
     * @param otherId 被删除者id1
     * @return 返回删除的个数
     * @throws DataAccessException 数据访问错误
     */
    int deleteFriend(Integer id, Integer otherId) throws DataAccessException;
}

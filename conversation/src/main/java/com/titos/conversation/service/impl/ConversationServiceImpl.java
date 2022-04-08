package com.titos.conversation.service.impl;

import com.titos.conversation.dao.ConversationDao;
import com.titos.conversation.po.MaxIncrementIdPO;
import com.titos.conversation.po.MessagePO;
import com.titos.conversation.service.ConversationService;
import com.titos.conversation.vo.SimpleInformationVO;
import com.titos.info.global.CommonResult;
import com.titos.info.global.enums.StatusEnum;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author: ddgo
 * @DateTime: 2022/4/3 20:11
 * @Version: 1.0.0
 * @Description: TODO 利用websocket 进行传输
 */
@Service
public class ConversationServiceImpl implements ConversationService {

    @Resource
    ConversationDao conversationDao;

    /**
     * 返回所有两个用户的所有聊天信息,失败则返回空
     * TODO 当前用户接收了所有信息，修改另一个用户的所有发送信息is_complete=1
     * @param id 发起者的id
     * @param otherId 接收者的id
     * @return
     */
    @Override
    public List<MessagePO> selectAllDialog(Integer id, Integer otherId) {
        List<MessagePO> messagePoList = null;
        try {
            messagePoList = conversationDao.selectAllDialog(id, otherId);
        } catch (DataAccessException e) {
            return null;
        }
        return messagePoList;
    }

    /**
     * 删除先把两个好友的联系删除，再删除所有的信息，必须同时成功。
     * @param id 发起者id
     * @param otherId 被删除者id
     * @return
     */
    @Override
    @Transactional
    public int deleteDialog(Integer id, Integer otherId) {
        int cnt;
        try {
            conversationDao.deleteFriend(id, otherId);
            cnt = conversationDao.deleteDialog(id, otherId);
        } catch (DataAccessException e) {
            // 捕获了异常，手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return -1;
        }
        return cnt;
    }

    /**
     * 发送信息，失败手动
     * @param id 发送者id
     * @param otherId 接收者id
     * @param message 发送的消息
     * @param isComplete 是否成功
     * @return
     */
    @Transactional
    @Override
    public int sendDialog(Integer id, Integer otherId, String message, Integer isComplete) {
        int cnt = 0;
        MaxIncrementIdPO maxIncrementIdPO = new MaxIncrementIdPO();
        try {
            cnt = conversationDao.insertDialog(id, otherId, message, isComplete, maxIncrementIdPO);
            conversationDao.updateFriend(id, otherId, maxIncrementIdPO.getMessageId());
        } catch (DataAccessException e) {
            // 捕获了异常，手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            cnt = -1;
        }
        return cnt;
    }

    /**
     * 添加好友
     * TODO 先查询是否加入了连接表里面。
     * @param id 添加者
     * @param otherId 被添加者
     * @return CommonResult<Boolean>
     */
    @Override
    public int addFriend(Integer id, Integer otherId) {
        int cnt = 0;
        try {
            cnt = conversationDao.insertFriend(id, otherId);
        } catch (DataAccessException e) {
            cnt = -1;
        }
        return cnt;
    }

    @Override
    public List<MessagePO> selectAllDialogReceiveNotComplete(Integer id, Integer otherId) {
        List<MessagePO> list = null;
        try {
            list = conversationDao.selectAllDialogReceiveNotComplete(id, otherId);
        } catch (DataAccessException e) {
            e.printStackTrace();
            list = null;
        }
        return list;
    }

    @Override
    public int isFriend(Integer id, Integer otherId) {
        int cnt = 0;
        try {
            cnt = conversationDao.selectFriend(id, otherId);
        } catch (DataAccessException e) {
            e.printStackTrace();
            cnt = -1;
        }
        return cnt;
    }

    @Override
    public int updateFriend(Integer id, Integer otherId, Integer maxId) {
        int cnt = 0;
        try {
            cnt = conversationDao.updateFriend(id, otherId, maxId);
        } catch (DataAccessException e) {
            e.printStackTrace();
            cnt = -1;
        }
        return cnt;
    }

    @Override
    public int updateComplete(Integer id, Integer otherId) {
        int cnt = 0;
        try {
            cnt = conversationDao.updateComplete(id, otherId);
        } catch (DataAccessException e) {
            e.printStackTrace();
            cnt = -1;
        }
        return cnt;
    }

    @Override
    public List<SimpleInformationVO> getSimpleInformation(Integer id) {
        List<SimpleInformationVO> simpleInformationVO = null;
        try {
            simpleInformationVO = conversationDao.getSimpleInformation(id);
        } catch (DataAccessException e) {
            simpleInformationVO = null;
        }
        return simpleInformationVO;
    }

}

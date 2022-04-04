package com.titos.conversation.service.impl;

import com.titos.conversation.dao.ConversationDao;
import com.titos.conversation.po.MessagePO;
import com.titos.conversation.service.ConversationService;
import com.titos.info.global.CommonResult;
import com.titos.info.global.enums.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: ddgo
 * @DateTime: 2022/4/3 20:11
 * @Version: 1.0.0
 * @Description:
 */
@Service
public class ConversationServiceImpl implements ConversationService {

    @Resource
    ConversationDao conversationDao;

    @Override
    public CommonResult<List<MessagePO>> selectAllDialog(Integer id, Integer otherId) {
        List<MessagePO> messagePOList = conversationDao.selectAllDialog(id, otherId);
        return new CommonResult<>(true, StatusEnum.SUCCESS.getCode(), StatusEnum.SUCCESS.getMsg(), messagePOList);
    }

    @Override
    public CommonResult<Boolean> deleteDialog(Integer id, Integer otherId) {
        return null;
    }

    @Override
    public CommonResult<Boolean> sendDialog(Integer id, Integer otherId, String message) {
        return null;
    }

    @Override
    public CommonResult<Boolean> addFriend(Integer id, Integer otherId) {
        return null;
    }
}

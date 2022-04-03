package com.titos.conversation.service;

import com.titos.conversation.po.Message;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Author: ddgo
 * @DateTime: 2022/4/1 20:47
 * @Version: 1.0.0
 * @Description:
 */
@Service
public interface ConversationService {

    public List<Message> selectAllConnection();
}

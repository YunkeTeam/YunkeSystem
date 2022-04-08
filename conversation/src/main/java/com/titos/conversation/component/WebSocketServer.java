package com.titos.conversation.component;

import com.alibaba.fastjson.JSONObject;
import com.titos.conversation.config.CustomSpringConfigurator;
import com.titos.conversation.dao.ConversationDao;
import com.titos.conversation.po.MessagePO;
import com.titos.conversation.vo.ConnectionVO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: ddgo
 * @DateTime: 2022/4/7 23:30
 * @Version: 1.0.0
 * @Description:
 */
@ServerEndpoint(value = "/chat/{id}/{toId}",configurator = CustomSpringConfigurator.class)
@Component
public class WebSocketServer {
    private static ConcurrentHashMap<Integer,Session> webSocketMap = new ConcurrentHashMap<>();
    private Session session;
    @Resource
    ConversationDao conversationDao;

    /**
     * 开启连接
     * @param session
     * @param toId
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id, @PathParam("toId") String toId) {
        this.session = session;
        int userId = Integer.parseInt(id);
        if(webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            webSocketMap.put(userId, this.session);
        }else {
            webSocketMap.put(userId, this.session);
        }
        // 从数据库里面拿看是否有toId 向 id 发离线消息
        List<MessagePO> messagePOList = conversationDao.selectAllDialogReceiveNotComplete(Integer.parseInt(toId), userId);
        if(messagePOList != null) {
            try {
                for(MessagePO messagePO : messagePOList) {
                    conversationDao.updateComplete(Integer.parseInt(toId), userId);
                    sendMessage(messagePO.getContent());
                }
            } catch (IOException e) {
                System.out.println("接收失败");
            }
        }
    }

    @OnClose
    public void onClose(@PathParam("id") String id) {
        Integer userId = Integer.parseInt(id);
        if(webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
        }
    }

    @OnMessage
    public void onMessage(@PathParam("id") String id, @PathParam("toId") String toId1, String message) {
        if(message == null || "".equals(message)) {
            return;
        }
        Integer userId = Integer.parseInt(id);
        Integer toId = Integer.parseInt(toId1);
        if(webSocketMap.containsKey(toId)) {
            System.out.println(webSocketMap.size());
            try {
                webSocketMap.get(toId).getBasicRemote().sendText(message);
                conversationDao.insertDialog(userId, toId, message, 1);
            } catch (IOException e) {
                System.out.println("发送失败");
            }
        } else {
            // 不在线，存到数据库，redis，保存为0
            conversationDao.insertDialog(userId, toId, message, 0);
        }

    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

}

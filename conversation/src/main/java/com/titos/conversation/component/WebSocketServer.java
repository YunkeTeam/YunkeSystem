package com.titos.conversation.component;

import com.titos.conversation.config.CustomSpringConfigurator;
import com.titos.conversation.dao.ConversationDao;
import com.titos.conversation.po.MessagePO;
import com.titos.conversation.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: ddgo
 * @DateTime: 2022/4/7 23:30
 * @Version: 1.0.0
 * @Description:
 */
@ServerEndpoint(value = "/conversation/chat/{id}/{toId}",configurator = CustomSpringConfigurator.class)
@Component
public class WebSocketServer {
    private static ConcurrentHashMap<Integer,Session> webSocketMap = new ConcurrentHashMap<>();
    private Session session;
    @Autowired
    ConversationService conversationService;

    /**
     * 建立连接时的处理，利用session通信
     * @param session OnOpen 处理session
     * @param id 发起者id
     * @param toId 建立者id
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id, @PathParam("toId") String toId) {
        this.session = session;
        int userId = Integer.parseInt(id);
        // 将当前用户的 session 加入到 map 里面
        if(webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            webSocketMap.put(userId, this.session);
        }else {
            webSocketMap.put(userId, this.session);
        }
        // 从数据库里面拿看是否有 toId 向 id 发送过离线消息，按时间顺序从小到大
        List<MessagePO> messagePOList = conversationService.selectAllDialogReceiveNotComplete(Integer.parseInt(toId), userId);
        if(messagePOList != null) {
            // 通过session 发送信息，发送成功则将数据库的is_complete 设置为 1
            try {
                for(MessagePO messagePO : messagePOList) {
                    conversationService.updateComplete(Integer.parseInt(toId), userId);
                    sendMessage(messagePO.getContent());
                }
            } catch (IOException e) {
                System.out.println("接收失败");
            }
        }
    }

    @OnClose
    public void onClose(@PathParam("id") String id) {
        // 关闭时，将用户的 session 删除
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
            // 在线，则通过 session 的
            try {
                conversationService.sendDialog(userId, toId, message, 1);
                webSocketMap.get(toId).getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // 不在线，存到数据库，保存为 0
            conversationService.sendDialog(userId, toId, message, 1);
        }
    }

    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getAsyncRemote().sendText(message);
    }

}

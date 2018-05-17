package com.tangly.service.impl;

import com.tangly.entity.MessageBean;
import com.tangly.enums.EMessageType;
import com.tangly.service.IWebSocketService;
import com.tangly.websocket.WebSocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * date: 2018/5/15 12:10 <br/>
 *
 * @author Administrator
 * @since JDK 1.7
 */
@Slf4j
@Service
public class WebSocketServiceImpl implements IWebSocketService {

    private static AtomicInteger onlineCount = new AtomicInteger(0);
    private ConcurrentHashMap<String, WebSocket> webSocketMap = new ConcurrentHashMap<>();


    @Override
    public int getOnlineCount() {
        return onlineCount.intValue();
    }

    @Override
    public int broadcastMessageToAll(MessageBean messageBean) {
        Set<Map.Entry<String, WebSocket>> entrySet = webSocketMap.entrySet();
        int successCount = 0;
        for (Map.Entry<String, WebSocket> entry : entrySet) {
            // 不发给自己
            WebSocket toWebSocket = entry.getValue();
            String from = messageBean.getFrom();
            if(!from.equals(toWebSocket.getUsername())){
                successCount += entry.getValue().sendMessageToThis(messageBean);
            }
        }
        return successCount;
    }

    @Override
    public int deliverMessage(MessageBean messageBean) {
        log.info("WebSocket消息 {} ", messageBean);
        if (messageBean.getType().equals(EMessageType.BROADCAST)) {
            return broadcastMessageToAll(messageBean);
        } else {
            WebSocket client = webSocketMap.get(messageBean.getTo());
            if (ObjectUtils.isEmpty(client)) {
                //接收方不在线
                client = webSocketMap.get(messageBean.getFrom());
                return client.sendMessageToThis(new MessageBean("系统", client.getUsername(), "对方不在线", EMessageType.SYSTEM));
            } else {
                return client.sendMessageToThis(messageBean);
            }
        }
    }

    @Override
    public void addWebSocket(WebSocket webSocket) {
        webSocketMap.put(webSocket.getUsername(), webSocket);
        onlineCount.addAndGet(1);
        log.info("有新连接加入 {} ！当前在线人数为 {}", webSocket.getUsername(), getOnlineCount());
    }

    @Override
    public void removeWebSocket(WebSocket webSocket) {
        webSocketMap.remove(webSocket.getUsername());
        onlineCount.decrementAndGet();
        log.info("有一连接关闭 {} ！当前在线人数为 {}", webSocket.getUsername(), getOnlineCount());

    }

}

package com.tangly.service.impl;

import com.tangly.service.IWebSocketService;
import com.tangly.websocket.WebSocket;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

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
    public int broadcastMessageToAll(String from, String msg) {
        Set<Map.Entry<String, WebSocket>> entrySet = webSocketMap.entrySet();
        int successCount = 0;
        for (Map.Entry<String, WebSocket> entry : entrySet) {
            successCount += entry.getValue().sendMessageToThis(from, msg);
        }
        return successCount;
    }

    @Override
    public int sendMessageTo(String from, String to, String type, String msg) {
        log.info("WebSocket消息 from: {}  ; to: {} ; type: {} ; msg: {} ", from, to, type, msg);
        if (StringUtils.isEmpty(to) || StringUtils.isEmpty(type)) {
            return broadcastMessageToAll(from, msg);
        } else {
            WebSocket client = webSocketMap.get(to);
            return client.sendMessageToThis(from, msg);
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

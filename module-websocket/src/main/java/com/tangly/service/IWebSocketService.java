package com.tangly.service;

import com.tangly.entity.MessageBean;
import com.tangly.websocket.WebSocket;

/**
 * date: 2018/5/15 11:39 <br/>
 *
 * @author Administrator
 * @since JDK 1.7
 */
public interface IWebSocketService {

    /**
     * 获取当前在线人数
     * @return
     */
    int getOnlineCount();

    /**
     * 广播群发
     * @param messageBean
     * @return
     */
    int broadcastMessageToAll(MessageBean messageBean);

    /**
     * 处理消息
     * @param messageBean
     * @return
     */
    int deliverMessage(MessageBean messageBean);

    /**
     * 添加一个WebSocket客户端
     * @param webSocket
     */
    void addWebSocket(WebSocket webSocket);


    /**
     * 添加一个WebSocket客户端
     * @param webSocket
     */
    void removeWebSocket(WebSocket webSocket);


}

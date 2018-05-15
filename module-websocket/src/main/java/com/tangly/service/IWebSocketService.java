package com.tangly.service;

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
     * @param from
     * @param msg
     * @return
     */
    int broadcastMessageToAll(String from ,String msg);

    /**
     * 单发消息
     * @param from
     * @param to
     * @param type
     * @param msg
     * @return
     */
    int sendMessageTo(String from ,String to, String type, String msg);

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

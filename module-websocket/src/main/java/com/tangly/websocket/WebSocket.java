package com.tangly.websocket;

import com.alibaba.fastjson.JSONObject;
import com.tangly.entity.MessageBean;
import com.tangly.enums.EMessageType;
import com.tangly.service.IWebSocketService;
import com.tangly.shiro.jwt.JWTUtil;
import com.tangly.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * @author tangly
 */
@Slf4j
@ServerEndpoint(value = "/endpoint/{token}")
@Component
@Scope("Prototype")
public class WebSocket {

    @Autowired
    private IWebSocketService iWebSocketService = (IWebSocketService) SpringUtil.getBean(IWebSocketService.class);

    private String token;

    private Session session;

    private String username;

    public String getUsername() {
        return username;
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(@PathParam("token") String token, Session session) {
        this.session = session;
        this.token = token;
        this.username = JWTUtil.getUsername(token);
        if (StringUtils.isEmpty(username)) {
            this.username = "匿名用户" + UUID.randomUUID().toString();
        }
        iWebSocketService.addWebSocket(this);

        MessageBean messageBean = new MessageBean();
        messageBean.setFrom("系统");
        messageBean.setType(EMessageType.SYSTEM);
        messageBean.setSendTime(new Date());
        messageBean.setTo(this.getUsername());
        messageBean.setMsg("用户 [ " + this.username + " ] 欢迎连接 ");
        iWebSocketService.deliverMessage(messageBean);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        iWebSocketService.removeWebSocket(this);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        MessageBean messageBean = JSONObject.parseObject(message,MessageBean.class);
        messageBean.setSendTime(new Date());
        messageBean.setFrom(this.getUsername());
        iWebSocketService.deliverMessage(messageBean);
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误", error);
        error.printStackTrace();
    }

    /**
     * 给该WebSocket发送信息
     * @param messageBean
     */
    public int sendMessageToThis(MessageBean messageBean) {
        try {
            this.session.getBasicRemote().sendText(JSONObject.toJSONString(messageBean));
            return 1;
        } catch (IOException e) {
            log.error("单发消息异常", e);
            return 0;
        }
    }


}
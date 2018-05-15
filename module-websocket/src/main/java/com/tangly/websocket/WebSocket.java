package com.tangly.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tangly.bean.ResponseBean;
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
        iWebSocketService.sendMessageTo(this.username, "welcome", JSON.toJSONString(ResponseBean.success("欢迎连接 【"+ this.username+"】", null)));
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
        JSONObject json = (JSONObject) JSON.parse(message);
        String msg = JSON.toJSONString(ResponseBean.success(json.getString("msg"), null));
        String type = json.getString("type");
        String to = json.getString("to");
        iWebSocketService.sendMessageTo(to, type, msg);
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误",error);
        error.printStackTrace();
    }

    /**
     * 给该WebSocket发送信息
     *
     * @param message
     * @throws IOException
     */
    public int sendMessageToThis(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
            return 1;
        } catch (IOException e) {
            log.error("单发消息异常",e);
            return 0;
        }
    }


}
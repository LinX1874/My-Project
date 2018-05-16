package com.tangly.controller;

import com.tangly.entity.MessageBean;
import com.tangly.entity.UserAuth;
import com.tangly.enums.EMessageType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

/**
 * date: 2018/5/14 16:17 <br/>
 *
 * @author tangly
 * @since JDK 1.7
 */
@Controller
@Api(description = "演示页面跳转地址", tags = "Socket通讯模块")
public class WebsocketController {

    @ApiOperation(value = "地址栏输入/websocket，跳转到WebSocket演示页面")
    @GetMapping("/websocket")
    public String websocket() {
        return "websocket";
    }

    @ApiOperation(value = "websocket连接方式说明", notes = "该接口文档页面无法模拟websocket接口，该方法只提供说明：  \n" +
            " 【创建websocket连接方式】：连接地址： 'ws://[域名]:[端口号]/endpoint/[登录时传回的token]'；  \n" +
            " 【通信格式】 与服务端通信参数格式为如下 MessageBean：")
    @ResponseBody
    @PostMapping("/endpoint/{token}")
    public MessageBean doc(@RequestBody MessageBean messageBean) {
        if (!ObjectUtils.isEmpty(SecurityUtils.getSubject().getPrincipal())) {
            UserAuth userAuth = (UserAuth) SecurityUtils.getSubject().getPrincipal();
            return new MessageBean("系统", userAuth.getUserInfo().getUserNickname(), "系统收到你的消息'" + messageBean.getMsg() + "'", EMessageType.SYSTEM);
        } else {
            return new MessageBean("系统", "匿名用户" + System.currentTimeMillis(), "系统收到你的消息'" + messageBean.getMsg() + "'", EMessageType.SYSTEM);
        }
    }

}

package com.tangly.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * date: 2018/5/14 16:17 <br/>
 *
 * @author tangly
 * @since JDK 1.7
 */
@Controller
@Api(description = "webSocket演示页面")
public class WebsocketController {

    @ApiOperation(value = "跳转到WebSocket测试页面")
    @GetMapping("/websocket")
    public String websocket() {
        return "websocket";
    }

}

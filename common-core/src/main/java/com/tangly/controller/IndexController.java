package com.tangly.controller;

import com.tangly.bean.ErrorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author tangly
 * @date 2018/4/17
 */
@Controller
@Api(description = "服务器维护页面跳转",tags = "后台页面跳转")
public class IndexController {

    /**
     * 重定向到主题模板
     * @return
     */
    @GetMapping("/")
    @ApiOperation(value = "访问后台主题演示页面")
    public String index() {
        return "index";
    }

    @GetMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    @ApiOperation(value = "访问无权限跳转",hidden = true)
    public ErrorResponse unauthorized(@RequestParam(required = false) String err) {
        return new ErrorResponse(10003, "无权访问或登录过期", err);
    }

    /**
     * 重定向到主题模板
     * @return
     */
    @GetMapping("/druid")
    @ApiOperation(value = "数据库监控")
    public String druid() {
        return "druid";
    }

    /**
     * 重定向到主题模板
     * @return
     */
    @GetMapping("/swagger")
    @ApiOperation(value = "接口文档页面")
    public String swagger() {
        return "redirect:/swagger-ui.html";
    }
}

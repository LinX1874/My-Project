package com.tangly;

import com.tangly.bean.ResponseBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
@Api(description = "页面跳转")
public class IndexController {

    /**
     * 重定向到主题模板
     * @return
     */
    @GetMapping("theme")
    @ApiOperation(value = "访问主题模板目录")
    public String theme() {
        return "redirect:./static/theme/index.html";
    }

    @ApiOperation(value = "跳转到登录页面")
    @GetMapping(value = "login")
    public String login() {
        Subject s = SecurityUtils.getSubject();
        return s.isRemembered() || s.isAuthenticated() ? "redirect:/" : "login";
    }

    @ApiOperation(value = "跳转到登出页面")
    @GetMapping(value = "logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "logout";
    }

    @GetMapping("/websocket")
    public String websocket() {
        return "websocket";
    }

    @GetMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseBean unauthorized(@RequestParam(required = false) String err) {
        return new ResponseBean(401, "无权访问或登录过期", err);
    }
}

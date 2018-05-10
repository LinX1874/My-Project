package com.tangly.controller;

import com.tangly.bean.ResponseBean;
import com.tangly.entity.UserAuth;
import com.tangly.service.IUserAuthService;
import com.tangly.shiro.jwt.JWTUtil;
import com.tangly.util.PasswordHelper;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

//import com.tangly.config.shiro.jwt.JWTUtil;


/**
 * @author tangly
 */
@RestController
@Api(description = "登录授权控制器")
@Slf4j
public class SignController {

    @Autowired
    private IUserAuthService userAuthService;

    @Autowired
    private PasswordHelper passwordHelper;

    /**
     * 过期时间24小时
     */
    private static final long EXPIRE_TIME_ONE_DAY = 24 * 60 * 60 * 1000;

    @ApiOperation(value = "用户登录")
    @PostMapping("/signIn")
    public ResponseBean signIn(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {

        UserAuth userAuth = userAuthService.getUserAuth(username);
        log.info("用户{} 登录成功", username);
        if (ObjectUtils.isEmpty(userAuth)) {
            return ResponseBean.success("用户名或密码错误", null);
        } else if (!passwordHelper.verifyPassword(password, userAuth)) {
            return ResponseBean.success("用户名或密码错误", null);
        }

        Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_TIME_ONE_DAY);

        //签发token
        return new ResponseBean(200, "Login success", JWTUtil.sign(username, passwordHelper.encryptPassword(password, userAuth), expireDate));

    }


    @ApiOperation(value = "用户注册")
    @PostMapping("/signUp")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userAuth", value = "用户账号实体", required = true, dataType = "UserAuth")
    })
    @ApiResponse(code = 202, message = "无法创建用户")
    public ResponseBean signUp(@RequestBody UserAuth userAuth) {
        if (userAuthService.existUserName(userAuth.getLoginAccount())) {
            return new ResponseBean(HttpStatus.OK.value(), "用户名已存在", "");
        }

        userAuthService.registerUserAuth(userAuth);

        return new ResponseBean(HttpStatus.ACCEPTED.value(), "注册成功", "");
    }


    @ApiOperation(value = "修改密码")
    @PostMapping("/updatePassword")
    @ApiResponse(code = 202, message = "无法创建用户")
    public ResponseBean updatePassword(@RequestParam String oldPassword , @RequestParam String newPassword) {
        UserAuth userAuth = (UserAuth) SecurityUtils.getSubject();

        if(!passwordHelper.verifyPassword(oldPassword,userAuth)){
            return ResponseBean.success("密码验证错误", null);
        }else{
        }

        return new ResponseBean(HttpStatus.ACCEPTED.value(), "注册成功", "");
    }



}

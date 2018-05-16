package com.tangly.controller;

import com.tangly.bean.ErrorResponse;
import com.tangly.entity.UserAuth;
import com.tangly.exception.NormalException;
import com.tangly.response.SignInResponse;
import com.tangly.service.IUserAuthService;
import com.tangly.shiro.jwt.JWTUtil;
import com.tangly.util.PasswordHelper;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author tangly
 */
@RestController
@Api(description = "登录授权控制器",tags = "用户中心模块")
@Slf4j
@ApiResponses({@ApiResponse(code = 409, message = "业务逻辑异常", response = ErrorResponse.class)})
public class SignController {

    @Autowired
    private IUserAuthService iUserAuthService;

    @Autowired
    private PasswordHelper passwordHelper;

    /**
     * 过期时间24小时
     */
    private static final long EXPIRE_TIME_ONE_DAY = 24 * 60 * 60 * 1000;

    @ApiOperation(value = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", required = true)
    })
    @PostMapping("/signIn")
    public SignInResponse signIn(
            @RequestParam("username") String username,
            @RequestParam("password") String password, HttpServletRequest request
    ) throws NormalException {

        UserAuth userAuth = iUserAuthService.getUserAuth(username);
        log.info("用户 {} 尝试登录", username);
        if (ObjectUtils.isEmpty(userAuth)) {
            //TODO 为了防止用户暴力破解这里需要把反复尝试的ip拉入黑名单
            String requestIP = request.getRemoteAddr();
            throw new NormalException("用户名或密码错误");
        } else if (!passwordHelper.verifyPassword(password, userAuth)) {
            userAuth.setLastLoginTryCount(userAuth.getLastLoginTryCount() + 1);
            iUserAuthService.updateByPrimaryKeySelective(userAuth);
            throw new NormalException("用户名或密码错误");
        } else {
            Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_TIME_ONE_DAY);
            String token = JWTUtil.sign(username, passwordHelper.encryptPassword(password, userAuth), expireDate);
            userAuth.setLastLoginTime(new Date());
            userAuth.setLastLoginToken(token);
            userAuth.setLastLoginIp(request.getRemoteAddr());
            userAuth.setLastLoginTryCount(0);
            iUserAuthService.updateByPrimaryKeySelective(userAuth);
            return new SignInResponse(token,userAuth.getUserInfo());
        }
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/signUp")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userAuth", value = "用户账号实体", required = true, dataType = "UserAuth")
    })
    public String signUp(@RequestBody UserAuth userAuth) throws NormalException {

        log.info("用户 {} 尝试注册", userAuth.getLoginAccount());

        if (iUserAuthService.existUserName(userAuth.getLoginAccount())) {
            throw new NormalException("用户名已存在");
        }
        //创建账号信息
        if (iUserAuthService.registerUserAuth(userAuth) ==1 ){
            return "注册成功";
        }else{
            throw new NormalException("注册失败");
        }
    }


    @ApiOperation(value = "修改密码")
    @PostMapping("/updatePassword")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldPassword", value = "旧密码", paramType = "query", required = true),
            @ApiImplicitParam(name = "newPassword", value = "新密码", paramType = "query", required = true)
    })
    public String updatePassword(@RequestParam String oldPassword, @RequestParam String newPassword) throws NormalException {

        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            UserAuth userAuth = (UserAuth) subject.getPrincipal();
            log.info("用户 {} 尝试修改密码", userAuth.getLoginAccount());
            if (!passwordHelper.verifyPassword(oldPassword, userAuth)) {
                //TODO 多次密码验证错误要将用户退出登录
                throw new NormalException("密码验证错误");
            } else {
                userAuth.setLoginPassword(newPassword);
                passwordHelper.encryptNewPassForUser(userAuth);
                if (iUserAuthService.updateByPrimaryKeySelective(userAuth) > 0) {
                    return "密码修改成功";
                } else {
                    throw new NormalException("密码修改失败");
                }
            }
        } else {
            throw new NormalException("登录信息无效");
        }
    }

    @RequiresAuthentication
    @DeleteMapping("/signOut")
    @ApiOperation(value = "退出登录")
    public String signOut() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
            return "退出登录";
        } else {
            return "无需登出";
        }
    }
}

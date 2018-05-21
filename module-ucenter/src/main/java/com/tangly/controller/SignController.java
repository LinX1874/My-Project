package com.tangly.controller;

import com.tangly.bean.ErrorResponse;
import com.tangly.entity.SysRole;
import com.tangly.entity.UserAuth;
import com.tangly.entity.UserInfo;
import com.tangly.enums.ELoginType;
import com.tangly.exception.NormalException;
import com.tangly.response.SignInResponse;
import com.tangly.service.ISysRoleService;
import com.tangly.service.IUserAuthService;
import com.tangly.shiro.jwt.JWTUtil;
import com.tangly.util.GetPlaceUtil;
import com.tangly.util.PasswordHelper;
import com.tangly.util.TimeUtil;
import com.tangly.util.ValidateUtil;
import com.tangly.util.gif.Captcha;
import com.tangly.util.gif.GifCaptcha;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author tangly
 */
@RestController
@Api(description = "登录授权控制器", tags = "用户中心模块")
@Slf4j
@ApiResponses({@ApiResponse(code = 206, message = "业务逻辑无法执行", response = ErrorResponse.class)})
public class SignController {

    @Autowired
    private IUserAuthService iUserAuthService;

    @Autowired
    private ISysRoleService iSysRoleService;

    @Autowired
    private PasswordHelper passwordHelper;

    /**
     * 过期时间24小时
     */
    private static final long EXPIRE_TIME_ONE_DAY = 24 * 60 * 60 * 1000;

    @ApiOperation(value = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", required = true),
            @ApiImplicitParam(name = "loginType", value = "登录类型", paramType = "query", required = true,  dataTypeClass = ELoginType.class)
    })
    @PostMapping("/signIn")
    public SignInResponse signIn(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("loginType") ELoginType loginType,
            HttpServletRequest request
    ) throws NormalException {

        System.out.println(loginType);

        //TODO 为了防止用户暴力破解这里需要把反复尝试的ip拉入黑名单
        String requestIP = request.getRemoteAddr();
        UserAuth userAuth = iUserAuthService.getUserAuth(username);
        log.info("用户 {} 尝试登录", username);
        if (ObjectUtils.isEmpty(userAuth)) {

            throw new NormalException("用户名或密码错误");

        } else if (!passwordHelper.verifyPassword(password, userAuth)) {

            iUserAuthService.updateLoginInfoFail(userAuth, requestIP);
            throw new NormalException("用户名或密码错误");

        } else {

            //签发Token，更新登录信息
            Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_TIME_ONE_DAY);
            String token = JWTUtil.sign(username, passwordHelper.encryptPassword(password, userAuth), expireDate);
            iUserAuthService.updateLoginInfoSuccess(userAuth, requestIP, token);

            String lastLoginTime = TimeUtil.formatTime(userAuth.getLastLoginTime(), "yyyy年MM月dd日 HH:mm:ss");
            String loginPlace = userAuth.getLastLoginIp();
            if (StringUtils.isNotEmpty(loginPlace)) {
                loginPlace = GetPlaceUtil.getPlace(loginPlace) + "(" + loginPlace + ")";
            }
            UserInfo userInfo = userAuth.getUserInfo();
            List<SysRole> sysRoles = iSysRoleService.getSysRole(userInfo.getId());

            return new SignInResponse(token, lastLoginTime, loginPlace, userInfo, sysRoles);
        }
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/signUp")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userAuth", value = "用户账号实体", required = true, dataType = "UserAuth")
    })
    public String signUp(@RequestBody UserAuth userAuth) throws NormalException {

        log.info("用户 {} 尝试注册", userAuth.getLoginAccount());
        ValidateUtil.validate(userAuth);
        if (iUserAuthService.existUserName(userAuth.getLoginAccount())) {
            throw new NormalException("用户名已存在");
        }
        //创建账号信息
        if (iUserAuthService.registerUserAuth(userAuth) == 1) {
            return "注册成功";
        } else {
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

    /**
     * 获取验证码（Gif版本）
     *
     * @param response
     */
    @ApiOperation(value = "获取图片验证码,这个接口会直接返回图片，文档测试工具中无法模拟")
    @RequestMapping(value = "getGifCode", method = RequestMethod.GET)
    public void getGifCode(HttpServletResponse response, HttpServletRequest request) {
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");
            /**
             * gif格式动画验证码
             * 宽，高，位数。
             */
            Captcha captcha = new GifCaptcha(146, 33, 4);
            //输出
            captcha.out(response.getOutputStream());
            HttpSession session = request.getSession(true);
            //存入Session
            session.setAttribute("_code", captcha.text().toLowerCase());
        } catch (Exception e) {
            log.error("获取验证码异常：{}", e.getMessage());
        }
    }
}

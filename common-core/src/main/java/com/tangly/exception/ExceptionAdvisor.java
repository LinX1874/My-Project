package com.tangly.exception;

import com.alibaba.fastjson.JSONException;
import com.tangly.bean.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * 全局处理Spring Boot的抛出异常。
 *
 * @author tangly
 */
@RestControllerAdvice
@Slf4j
public class ExceptionAdvisor {

    /**
     * 捕捉shiro未登录的异常
     *
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthenticatedException.class)
    public ErrorResponse handleUnauthenticated(UnauthenticatedException e) {
        log.error(e.getMessage());
        return new ErrorResponse(401, "请先登录", e);
    }

    /**
     * 捕捉shiro无权访问的异常
     *
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ErrorResponse handle403(UnauthorizedException e) {
        return new ErrorResponse(403, "无权访问", e);
    }


    /**
     * 捕捉shiro其它异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public ErrorResponse handle401(ShiroException e) {
        log.error("shiro", e);
        return new ErrorResponse(401, e.getMessage(), e);
    }

    @ExceptionHandler(JSONException.class)
    public ErrorResponse jsonException(JSONException e) {
        log.error("参数格式错误", e);
        return new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "参数校验异常", e.getMessage());
    }

    /**
     * 捕获所有参数校验异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse illegalArgument(IllegalArgumentException e) {
        return new ErrorResponse(HttpStatus.ACCEPTED.value(), e.getMessage(), e);
    }

    /**
     * 捕获所有业务逻辑代码抛出的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NormalException.class)
    public ErrorResponse normalException(NormalException e) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage(), e);
    }


    /**
     * 捕捉不知原因的异常(代理产生)
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(UndeclaredThrowableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse undeclaredThrowableException(Throwable ex) {
        Throwable e = ex.getCause();
        log.error("捕获其它异常 ", e);
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), ex);
    }

    /**
     * 捕捉其余所有异常
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse globalException(HttpServletRequest request, Throwable ex) {
        log.error("捕获全局异常 ", ex);
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ex);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
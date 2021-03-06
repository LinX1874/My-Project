package com.tangly.controller;

import com.github.pagehelper.PageHelper;
import com.tangly.bean.ErrorResponse;
import com.tangly.bean.PageResponse;
import com.tangly.bean.SearchParam;
import com.tangly.entity.HelloWorld;
import com.tangly.entity.UserAuth;
import com.tangly.entity.UserInfo;
import com.tangly.enums.ESort;
import com.tangly.exception.NormalException;
import com.tangly.service.IHelloWorldService;
import com.tangly.util.ValidateUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

/**
 * date: 2018/1/2 17:29 <br/>
 * 【@Api @ApiOperation @ApiImplicitParam】 是SwaggerUI的接口文档注解，详见： https://blog.csdn.net/xupeng874395012/article/details/68946676
 *
 * @author tangly
 * @since JDK 1.7
 */
@RestController
@RequestMapping(value = "/helloworld")
@Slf4j
@Api(description = "示例_HelloWorld_增删改查_Demo", tags = "DEMO演示模块")
@ApiResponses({@ApiResponse(code = 206, message = "业务逻辑无法执行", response = ErrorResponse.class)})
public class HelloWorldController {

    @Autowired
    private IHelloWorldService iHelloWorldService;

    //START----------------------------增删改查CRUD+PAGE-----------------------------------

    @ApiOperation(value = "获取HelloWorld列表", notes = "获取列表,在url中使用?page=xx&size=xx实现分页,不传默认是1,10")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", paramType = "query", value = "页码(不传默认是1)", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "size", paramType = "query", value = "页面大小(不传默认是10)", dataType = "int", defaultValue = "10"),
            @ApiImplicitParam(name = "sort", paramType = "query", value = "排序字段，sort=asc表示正序，sort=desc表示倒序(不传默认是正序)")
    })
    @GetMapping(value = "")
    public PageResponse<HelloWorld> getList(
            @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "10", required = false) Integer size,
            @RequestParam(name = "sort", required = false) ESort sort

    ) {
        PageHelper.startPage(page, size);
        SearchParam searchParam = new SearchParam();
        searchParam.addOrderBy("id", sort);
        PageResponse<HelloWorld> pageInfo = iHelloWorldService.selectByPage(searchParam, HelloWorld.class);
        return pageInfo;
    }

    @ApiOperation(value = "搜索HelloWorld列表", notes = "包含详细的分页排序参数")
    @PostMapping(value = "search")
    public PageResponse<HelloWorld> getList(@RequestBody SearchParam searchParam) {
        PageHelper.startPage(searchParam.getPage(), searchParam.getSize());
        PageResponse<HelloWorld> pageInfo = iHelloWorldService.selectByPage(searchParam, HelloWorld.class);
        return pageInfo;
    }


    @ApiOperation(value = "创建HelloWorld实体", notes = "根据HelloWorld对象创建HelloWorld")
    @ApiImplicitParam(name = "helloWorld", value = "id字段由服务端生成，前端不用传", required = true, dataType = "HelloWorld")
    @PostMapping(value = "")
    public HelloWorld postHelloWorld(@RequestBody HelloWorld helloWorld) {
        ValidateUtil.validate(helloWorld);
        helloWorld.setId(null);
        iHelloWorldService.insert(helloWorld);
        return helloWorld;
    }

    @ApiOperation(value = "更新完整HelloWorld实体", notes = "请传完整的helloworld对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "helloWorld", value = "HelloWorld完整实体", required = true, dataType = "HelloWorld")
    })
    @PutMapping()
    public HelloWorld putHelloWorld(@RequestBody HelloWorld helloWorld) {
        ValidateUtil.validate(helloWorld);
        iHelloWorldService.updateByPrimaryKey(helloWorld);
        return helloWorld;
    }

    @ApiOperation(value = "更新HelloWorld实体部分字段", notes = "不传的字段就不更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "helloWorld", value = "HelloWorld实体需要更新的字段", required = true, dataType = "HelloWorld")
    })
    @PatchMapping()
    public HelloWorld patchHelloWorld(@RequestBody HelloWorld helloWorld) {
        iHelloWorldService.updateByPrimaryKeySelective(helloWorld);
        return helloWorld;
    }

    @ApiOperation(value = "获取HelloWorld详细信息", notes = "根据url的id来获取HelloWorld详细信息")
    @ApiImplicitParam(paramType = "path", name = "id", dataType = "Integer")
    @GetMapping(value = "/{idpo}")
    public HelloWorld getHelloWorld(@PathVariable("id") Integer id) {
        HelloWorld helloWorld = iHelloWorldService.selectByPrimaryKey(id);
        return helloWorld;
    }

    @ApiOperation(value = "删除HelloWorld", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", dataType = "Integer")
    @DeleteMapping(value = "/{id}")
    public void deleteHelloWorld(@PathVariable("id") Integer id) {
        iHelloWorldService.deleteByPrimaryKey(id);
    }
    //END----------------------------增删改查CRUD+PAGE-----------------------------------


    //START-------------------------------用户权限相关-----------------------------------

    @GetMapping("/}auth/check_current_user")
    @ApiOperation(value = "查看当前登录的用户信息")
    public UserInfo checkCurrentUser() throws NormalException {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            UserAuth userAuth = (UserAuth) subject.getPrincipal();
            if (ObjectUtils.isEmpty(userAuth)) {
                throw new UnauthenticatedException("请重新登录");
            }
            UserInfo userInfo = userAuth.getUserInfo();
            return userInfo;
        } else {
            throw new NormalException("未登录用户");
        }
    }

    @GetMapping("/}auth/require_auth")
    @RequiresAuthentication
    @ApiOperation(value = "需要登录才能访问的接口")
    public String requireAuth() {
        return "账号已经登录";
    }

    @GetMapping("/}auth/require_role")
    @RequiresRoles("ROLE_ADMIN")
    @ApiOperation(value = "需要ROLE_ADMIN角色才能访问的接口")
    public String requireRole() {
        return "该账号具有 ROLE_ADMIN 的角色";
    }

    @GetMapping("/}auth/require_permission")
    @ApiOperation(value = "需要userInfo:view,userInfo:add权限才能访问的接口")
    @RequiresPermissions(logical = Logical.AND, value = {"userInfo:view", "userInfo:add"})
    public String requirePermission() {
        return "该账号具有 ROLE_ADMIN 角色的以下权限 require userInfo:view,userInfo:add";
    }

    //END-------------------------------用户权限相关-----------------------------------

}

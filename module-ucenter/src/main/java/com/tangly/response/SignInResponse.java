package com.tangly.response;

import com.tangly.entity.SysPermission;
import com.tangly.entity.SysRole;
import com.tangly.entity.UserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * date: 2018/5/16 16:57 <br/>
 *
 * @author Administrator
 * @since JDK 1.7
 */
@Data
@ApiModel(description = "登录成功的返回包")
public class SignInResponse {

    @ApiModelProperty(value = "秘钥", example = "123456789987654321abcdefg")
    String token;

    @ApiModelProperty(value = "上次登录时间", example = "2018年9月9日 8:30:00")
    String lastLoginTime;

    @ApiModelProperty(value = "上次登录地点", example = "福州")
    String lastLoginPlace;


    @ApiModelProperty(value = "用户信息")
    UserInfo userInfo;

    @ApiModelProperty(value = "系统角色")
    List<SysRole> roles;

    @ApiModelProperty(value = "系统权限")
    List<SysPermission> sysPermissions;

    public SignInResponse(String token, String lastLoginTime, String lastLoginPlace, UserInfo userInfo, List<SysRole> roles) {
        this.token = token;
        this.lastLoginTime = lastLoginTime;
        this.lastLoginPlace = lastLoginPlace;
        this.userInfo = userInfo;
        this.roles = roles;
    }
}

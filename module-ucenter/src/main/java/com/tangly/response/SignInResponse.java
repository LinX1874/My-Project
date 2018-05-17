package com.tangly.response;

import com.tangly.entity.UserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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

    public SignInResponse(String token, String lastLoginTime, String lastLoginPlace, UserInfo userInfo) {
        this.token = token;
        this.lastLoginTime = lastLoginTime;
        this.lastLoginPlace = lastLoginPlace;
        this.userInfo = userInfo;
    }
}

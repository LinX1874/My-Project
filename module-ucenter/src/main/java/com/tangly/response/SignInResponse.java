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

    @ApiModelProperty(value = "用户信息")
    UserInfo userInfo;

    public SignInResponse(String token, UserInfo userInfo) {
        this.token = token;
        this.userInfo = userInfo;
    }
}

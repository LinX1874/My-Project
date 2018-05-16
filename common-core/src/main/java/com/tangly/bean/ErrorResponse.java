package com.tangly.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author tangly
 */
@Data
@ApiModel(description = "错误信息返回对象")
@Component
public class ErrorResponse {

    /**
     * errorCode 服务端错误码
     * 10000 服务器异常
     * 10001 通用的业务异常代码
     * 10002 参数格式错误
     * 10003 未登录或权限不够
     */
    @ApiModelProperty(name = "errorCode", value = "服务端异常码", example = "10001")
    private int errorCode;

    /**
     * 返回提示消息
     */
    @ApiModelProperty(name = "errorText", value = "简单错误描述", example = "简单错误描述")
    private String errorText;

    /**
     * 异常的详细路径
     */
    @ApiModelProperty(name = "messageDebug", value = "详细异常定位,开发过程才会显示", example = "详细异常定位,开发过程才会显示")
    private Object messageDebug;

    @org.springframework.beans.factory.annotation.Value("&{spring.profiles.active}")
    @ApiModelProperty(hidden = true)
    private String active;

    public ErrorResponse() {
    }

    public ErrorResponse(int errorCode, String errorText, Object messageDebug) {
        this.errorCode = errorCode;
        this.errorText = errorText;
        if("dev".equals(active)){
            this.messageDebug = messageDebug;
        }
    }

}
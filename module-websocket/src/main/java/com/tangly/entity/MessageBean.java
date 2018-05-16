package com.tangly.entity;

import com.tangly.enums.EMessageType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * date: 2018/5/15 14:38 <br/>
 *
 * @author Administrator
 * @since JDK 1.7
 */
@Data
@ApiModel(description = "通信实体")
public class MessageBean {

    /**
     * 消息来源
     */
    @ApiModelProperty(value = "消息来源，客户端发送时不用填，服务端会自动判断",example = "小明")
    private String from;
    /**
     * 发送对象
     */
    @ApiModelProperty(value = "发送目标，填对方用户名，服务端转发消息",example = "张三")
    private String to;
    /**
     * 消息内容
     */
    @ApiModelProperty(value = "消息内容，任意格式",example = "你好")
    private String msg;
    /**
     * 消息类型
     */
    @ApiModelProperty(value = "消息类型枚举")
    private EMessageType type;

    /**
     * 发送时间
     */
    @ApiModelProperty(value = "发送日期服务器会判断，客户端发送时不需要填写")
    private Date sendTime;

    public MessageBean() {
    }

    public MessageBean(String from, String to, String msg, EMessageType type) {
        this.from = from;
        this.to = to;
        this.msg = msg;
        this.type = type;
    }
}

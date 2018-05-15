package com.tangly.entity;

import com.tangly.enums.EMessageType;
import lombok.Data;

import java.util.Date;

/**
 * date: 2018/5/15 14:38 <br/>
 *
 * @author Administrator
 * @since JDK 1.7
 */
@Data
public class MessageBean {

    /**
     * 消息来源
     */
    private String from;
    /**
     * 发送对象
     */
    private String to;
    /**
     * 消息内容
     */
    private String msg;
    /**
     * 消息类型
     */
    private EMessageType type;

    /**
     * 发送时间
     */
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

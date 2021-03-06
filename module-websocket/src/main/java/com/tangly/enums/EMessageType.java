package com.tangly.enums;

/**
 * date: 2018/5/15 15:09 <br/>
 * websocket消息类型
 *
 * @author Administrator
 * @since JDK 1.7
 */
public enum EMessageType {
    /**
     * 0 单发
     */
    SINGLE("单发"),
    /**
     * 1 群发广播
     */
    BROADCAST("群发广播"),

    /**
     * 2 系统提示
     */
    SYSTEM("系统消息");

    private String description;

    EMessageType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}

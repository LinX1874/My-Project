package com.tangly.enums;

import com.tangly.base.BaseEnum;
import io.swagger.annotations.ApiModel;

/**
 * 登录类型枚举类
 * @author tangly
 */
@ApiModel(value = "ELoginType",description = "登录类型枚举类")
public enum ELoginType implements BaseEnum {

    /**
     * 自定义用户名
     */
    USERNAME(0, "USERNAME"),
    /**
     * 手机号
     */
    PHONE(1, "PHONE"),
    /**
     * 微信号
     */
    WEIXIN(2, "WEXIN"),
    /**
     * qq号
     */
    QQ(3,"QQ"),
    /**
     * 邮箱号
     */
    EMAIL(4,"EMAIL");

    Integer value;
    String label;

    ELoginType(Integer value, String label) {
        this.label = label;
        this.value = value;
    }

    public static ELoginType enumOf(String label) {
        for (ELoginType s : ELoginType.values()) {
            if (label .equals(s.label)) {
                return s;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return label;
    }
}
package com.tangly.enums;

import com.tangly.base.BaseEnum;

/**
 * 示例用的枚举类
 * 例如数据库中的int字段 0,1,2 分别映射业务中的某种对象时; 请使用枚举，比如性别枚举
 * 必须包含 value label 这两个成员属性
 */
public enum EType implements BaseEnum {

    NORMAL(0,"普通用户"),

    VIP(1,"会员");

    Integer value;

    String label;

    EType(Integer value, String label) {
        this.label = label;
        this.value = value;
    }

    /**
     * 重写toString,fastjson转化枚举对象时使用toString方式输出json
     * @see com.tangly.config.mvc.WebConfig 第97行附近
     * @return
     */
    @Override
    public String toString() {
        return label;
    }
}

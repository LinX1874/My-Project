package com.tangly.enums;

import com.tangly.base.BaseEnum;

/**
 * 用户性别枚举类
 * 枚举对象直接是中文，这是个大胆的尝试
 */
public enum EUserSex implements BaseEnum {

    女(0, "女"),
    男(1, "男"),
    保密(2, "保密");

    Integer value;
    String label;

    EUserSex(Integer value, String label) {
        this.label = label;
        this.value = value;
    }

    @Override
    public String toString() {
        return label;
    }
}
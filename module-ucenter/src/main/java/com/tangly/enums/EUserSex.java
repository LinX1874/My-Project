package com.tangly.enums;

import com.tangly.base.BaseEnum;
import io.swagger.annotations.ApiModel;

/**
 * 用户性别枚举类
 */
public enum EUserSex implements BaseEnum {

    FEMALE(0, "女"),
    MALE(1, "男"),
    OTHER(2, "保密");

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
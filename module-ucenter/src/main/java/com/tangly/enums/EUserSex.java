package com.tangly.enums;

import com.tangly.base.BaseEnum;

/**
 * 用户性别枚举类
 * @author tangly
 */
public enum EUserSex implements BaseEnum {

    FEMALE(0, "女"),
    MALE(1, "男"),
    UNKNOW(2, "保密");

    Integer value;
    String label;

    EUserSex(Integer value, String label) {
        this.label = label;
        this.value = value;
    }

    public static EUserSex enumOf(String label) {
        for (EUserSex s : EUserSex.values()) {
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
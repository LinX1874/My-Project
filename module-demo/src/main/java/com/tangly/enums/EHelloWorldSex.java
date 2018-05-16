package com.tangly.enums;

import com.tangly.base.BaseEnum;

/**
 * helloworld性别枚举类
 * 枚举对象直接是中文，这是个大胆的尝试
 * @author tangly
 */
public enum EHelloWorldSex implements BaseEnum {

    FEMALE(0, "女"),
    MALE(1, "男"),
    UNKNOW(2, "保密");

    Integer value;
    String label;

    EHelloWorldSex(Integer value, String label) {
        this.label = label;
        this.value = value;
    }

    @Override
    public String toString() {
        return label;
    }

}
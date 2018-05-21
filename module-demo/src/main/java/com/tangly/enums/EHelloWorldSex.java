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

    /**
     * 前台传枚举类时直接传label字符串
     * 后台通过@JSONField(name="sex") public void setSex(){} 方法将label转为枚举对象
     * @param label
     * @return
     */
    public static EHelloWorldSex enumOf(String label) {
        for (EHelloWorldSex s : EHelloWorldSex.values()) {
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
package com.tangly.enums;

/**
 * date: 2018/5/18 16:13 <br/>
 * 排序枚举类型 ASC正序 DESC倒序
 * @author Administrator
 * @since JDK 1.7
 */
public enum ESort {

    asc(0,"正序"),

    desc(1,"倒序");

    Integer value;

    String label;

    ESort(Integer value, String label) {
        this.label = label;
        this.value = value;
    }

}

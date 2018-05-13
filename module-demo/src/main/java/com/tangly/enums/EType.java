package com.tangly.enums;

import com.tangly.base.BaseEnum;

/**
 * 示例用的枚举类
 * 例如数据库中的int字段 0,1,2 分别映射业务中的某种对象时请用枚举，比如性别枚举
 * 必须包含 value label 这两个成员属性
 */
public enum EType implements BaseEnum {

    EMPLOYEE(0,"员工"),

    BOSS(1,"老板");

    Integer value;
    String label;

    private EType(Integer value, String label) {
        this.label = label;
        this.value = value;
    }

    /**
     * 重写oString 方便控制台打印，不是必要的。
     * @return
     */
    @Override
    public String toString() {
        return label;
    }
}

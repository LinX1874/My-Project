package com.tangly.base;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @author tangly
 * 枚举类实现该接口，以实现mybatis中的枚举键值映射为枚举类
 */
public interface BaseEnum {

    String DEFAULT_VALUE_NAME = "value";

    String DEFAULT_LABEL_NAME = "label";

    default Integer getValue() {
        Field field = ReflectionUtils.findField(this.getClass(), DEFAULT_VALUE_NAME);
        if (field == null)
            return null;
        try {
            field.setAccessible(true);
            return Integer.parseInt(field.get(this).toString());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    default String getLabel() {
        Field field = ReflectionUtils.findField(this.getClass(), DEFAULT_LABEL_NAME);
        if (field == null){
            return null;
        }
        try {
            field.setAccessible(true);
            return field.get(this).toString();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    static <T extends Enum<T>> T valueOfEnum(Class<T> enumClass, Integer value) {
        if (value == null)
            throw  new IllegalArgumentException("BaseEnum value should not be null");
        if (enumClass.isAssignableFrom(BaseEnum.class))
            throw new IllegalArgumentException("illegal BaseEnum type");
        T[] enums = enumClass.getEnumConstants();
        for (T t: enums) {
            BaseEnum baseEnum = (BaseEnum)t;
            if (baseEnum.getValue().equals(value))
                return (T) baseEnum;
        }
        throw new IllegalArgumentException("cannot parse integer: " + value + " to " + enumClass.getName());
    }
}
package com.tangly.enums;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;

/**
 * date: 2018/5/16 12:31 <br/>
 * 测试枚举类的转换
 * @author Administrator
 * @since JDK 1.7
 */
public class EHelloWorldSexTest {

    @Test
    public void test(){
        EHelloWorldSex sex = EHelloWorldSex.MALE;
        System.out.println(sex.name());
        System.out.println(JSON.toJSONString(sex));
        System.out.println(JSON.toJSONString(EHelloWorldSex.MALE,SerializerFeature.WriteEnumUsingToString));;
    }

}
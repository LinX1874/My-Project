package com.tangly.entity;

import com.tangly.util.ValidateUtil;
import org.junit.Test;

/**
 * date: 2018/5/7 16:12 <br/>
 *
 * @author tangly
 * @since JDK 1.7
 */
public class HelloWorldTest {

    /**
     * 预期抛出参数非法异常
     */
    @Test(expected = IllegalArgumentException.class)
    public void testValidate() {

        HelloWorld helloWorld2 = HelloWorld.builder()
                .name("姓名")
                .phone("123456")
                .build();
        ValidateUtil.validate(helloWorld2);

    }

}
package com.tangly.service;

import com.tangly.DemoSpringTest;
import com.tangly.entity.HelloWorld;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Random;

/**
 * 单元测试
 * @author tangly
 * @version 1.0
 * @since <pre>01/02/2018</pre>
 */
public class IHelloWorldServiceImplSpringTest extends DemoSpringTest {

    @Autowired
    private IHelloWorldService iHelloWorldService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    @Transactional
    @Rollback
    public void saveAndGet() {
        iHelloWorldService.insert(
                HelloWorld.builder().name("测试名字").phoneNum("13800138000").build()
        );
        HelloWorld helloWorld ;
        helloWorld = iHelloWorldService.selectOne(HelloWorld.builder().name("测试名字").phoneNum("13800138000").build());
        Assert.assertNotNull(helloWorld);
        System.out.println(helloWorld);
    }

    @Test
    @Transactional
    public void selectByExample() {
        Random r = new Random();
        for (int i = 1; i <= 20; i++) {
            iHelloWorldService.insert(
                    HelloWorld.builder().name("测试名字"+String.valueOf(i)).phoneNum("12345678901").build()
            );
        }
        Example example = new Example(HelloWorld.class);
        example.setOrderByClause("id DESC");
        example.createCriteria().andEqualTo("phoneNum","12345678901");
        List<HelloWorld> list = iHelloWorldService.selectByExample(example);
        Assert.assertEquals(list.size(),20);
        System.out.println(list);
    }

}

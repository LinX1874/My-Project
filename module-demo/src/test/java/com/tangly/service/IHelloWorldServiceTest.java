package com.tangly.service;

import com.tangly.DemoApplication;
import com.tangly.entity.HelloWorld;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Random;

/**
 * Junit单元测试
 * @author tangly
 * @version 1.0
 * @since <pre>01/02/2018</pre>
 */
// @RunWith 使JUnit使用Spring的测试支持 SpringRunner是SpringJUnit4ClassRunner的新名字
@RunWith(SpringRunner.class)
// 创建DemoApplication的上下文，并支持Springboot特性
@SpringBootTest(classes = DemoApplication.class)
public class IHelloWorldServiceTest {

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
                    HelloWorld.builder().name("测试名字"+String.valueOf(i)).phoneNum("13800138000").build()
            );
        }
        Example example = new Example(HelloWorld.class);
        example.setOrderByClause("id DESC");
        example.createCriteria().andEqualTo("phoneNum","13800138000");
        List<HelloWorld> list = iHelloWorldService.selectByExample(example);
        Assert.assertEquals(list.size(),20);
        System.out.println(list);
    }

}

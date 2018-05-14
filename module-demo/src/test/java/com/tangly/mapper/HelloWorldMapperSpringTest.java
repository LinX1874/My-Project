package com.tangly.mapper;

import com.tangly.DemoSpringTest;
import com.tangly.entity.HelloWorld;
import org.apache.shiro.util.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tangly on 2018/4/15.
 */
public class HelloWorldMapperSpringTest extends DemoSpringTest {

    @Test
    @Transactional
    @Rollback
    public void addHelloWorld() throws Exception {
        helloWorldMapper.insert(
                HelloWorld.builder().name("世界你好").build()
        );
        HelloWorld helloWorld;
    }

    @Autowired
    HelloWorldMapper helloWorldMapper;

    @Test
    @Transactional
    @Rollback
    public void getAll() throws Exception {
        helloWorldMapper.insert(
                HelloWorld.builder().name("世界你好").build()
        );

        List<HelloWorld> helloWordlList = helloWorldMapper.selectAll();
        Assert.notNull(helloWordlList);
        System.out.println("helloWorld:" + helloWordlList);
    }

}
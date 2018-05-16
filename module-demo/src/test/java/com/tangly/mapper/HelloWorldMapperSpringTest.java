package com.tangly.mapper;

import com.tangly.DemoSpringTest;
import com.tangly.entity.HelloWorld;
import com.tangly.enums.EHelloWorldSex;
import com.tangly.enums.EType;
import com.tangly.util.RandomUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangly on 2018/4/15.
 */
public class HelloWorldMapperSpringTest extends DemoSpringTest {

    @Autowired
    HelloWorldMapper helloWorldMapper;

    @Test
    @Transactional
    @Rollback
    public void addHelloWorld() {
        HelloWorld helloWorld = HelloWorld.builder()
                .name("世界你好")
                .phone("13800138000")
                .build();
        Assert.assertEquals(1,helloWorldMapper.insert(helloWorld));

    }

    @Test
    @Transactional
    @Rollback
    public void insertListHelloWorld(){
        List<HelloWorld> helloWorldList = new ArrayList<>(100);
        for(int i =1;i<=100;i++){
            helloWorldList.add(
                    HelloWorld.builder()
                            .name(RandomUtil.getChineseName())
                            .sex(EHelloWorldSex.values()[RandomUtil.getNum(0,2)])
                            .address(RandomUtil.getRoad())
                            .email(RandomUtil.getEmail(10,15))
                            .phone(RandomUtil.getTel())
                            .type(EType.values()[RandomUtil.getNum(0,1)])
                            .build()
            );
        }
        Assert.assertEquals(100,helloWorldMapper.insertList(helloWorldList));
        System.out.println(helloWorldList);
    }


    @Test
    @Transactional
    @Rollback
    public void getAll() throws Exception {
        helloWorldMapper.insert(
                HelloWorld.builder().name("世界你好").build()
        );

        List<HelloWorld> helloWordlList = helloWorldMapper.selectAll();
        Assert.assertNotNull(helloWordlList);
        System.out.println("helloWorld:" + helloWordlList);
    }

}
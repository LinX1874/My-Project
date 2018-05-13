package com.tangly.service;

import com.tangly.UserCenterApplication;
import com.tangly.entity.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserCenterApplication.class)
public class IUserInfoServiceTest {

    @Autowired
    private IUserInfoService iUserInfoService;

    @Test
    public void testBase(){

        UserInfo userExample = UserInfo.builder().userNickname("哈哈 ").build();
        UserInfo userInfo = iUserInfoService.selectOne(userExample);
        assertNotNull(userInfo);
        System.out.println(userInfo);

    }


}

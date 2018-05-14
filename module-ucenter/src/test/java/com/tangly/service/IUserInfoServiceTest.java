package com.tangly.service;

import com.tangly.UserCenterSpringTest;
import com.tangly.entity.UserInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

public class IUserInfoServiceTest extends UserCenterSpringTest {

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

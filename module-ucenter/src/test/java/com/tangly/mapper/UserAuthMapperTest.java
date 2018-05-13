package com.tangly.mapper;

import com.tangly.UserCenterApplication;
import com.tangly.entity.UserAuth;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserCenterApplication.class)
public class UserAuthMapperTest {

    @Autowired
    private UserAuthMapper userAuthMapper;

    @Test
    public void getUserAuth() throws Exception {

        UserAuth userAuth = userAuthMapper.getUserAuth("testAccount");
        assertNotNull(userAuth);
        System.out.println(userAuth);

    }

}
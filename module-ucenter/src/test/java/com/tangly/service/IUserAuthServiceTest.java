package com.tangly.service;

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
public class IUserAuthServiceTest {

    @Autowired
    private IUserAuthService iUserAuthService;

    @Test
    public void getUserAuth() throws Exception {
        UserAuth authExample = UserAuth.builder().loginAccount("admin").build();
        UserAuth auth = iUserAuthService.selectOne(authExample);
        assertNotNull(auth);
        System.out.println(auth);
    }

    @Test
    public void registerUserAuth() throws Exception {
    }

    @Test
    public void existUserName() throws Exception {
    }

    @Test
    public void save() throws Exception {
    }

}
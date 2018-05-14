package com.tangly.service;

import com.tangly.UserCenterSpringTest;
import com.tangly.entity.UserAuth;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertNotNull;

public class IUserAuthServiceTest extends UserCenterSpringTest {

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
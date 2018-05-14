package com.tangly.mapper;

import com.tangly.UserCenterSpringTest;
import com.tangly.entity.UserAuth;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertNotNull;

public class UserAuthMapperTest extends UserCenterSpringTest {

    @Autowired
    private UserAuthMapper userAuthMapper;

    @Test
    public void getUserAuth() throws Exception {

        UserAuth userAuth = userAuthMapper.getUserAuth("testAccount");
        assertNotNull(userAuth);
        System.out.println(userAuth);

    }

}
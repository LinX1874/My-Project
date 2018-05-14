package com.tangly.mapper;

import com.tangly.UserCenterSpringTest;
import com.tangly.entity.UserInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertNotNull;

public class UserInfoMapperTest extends UserCenterSpringTest {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    public void test(){
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(1L);
        assertNotNull(userInfo);
        System.out.println(userInfo);
    }

}
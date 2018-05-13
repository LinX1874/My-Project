package com.tangly.mapper;

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
public class UserInfoMapperTest {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    public void test(){
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(1L);
        assertNotNull(userInfo);
        System.out.println(userInfo);
    }

}
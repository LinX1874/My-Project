package com.tangly

import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * date: 2018/5/14 16:03 <br/> 
 * 用户中心模块的spring单元测试集成此类
 * @author tangly
 * @since JDK 1.7 
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserCenterApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserCenterSpringTest {
}

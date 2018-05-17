package com.tangly.mapper;

import com.tangly.UserCenterSpringTest;
import com.tangly.entity.SysRole;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * date: 2018/5/17 16:44 <br/>
 *
 * @author Administrator
 * @since JDK 1.7
 */
public class SysRoleMapperTest extends UserCenterSpringTest {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Test
    public void getSysRole() {
        List<SysRole> roles =  sysRoleMapper.getSysRole(1L);
        System.out.println(roles);
    }
}
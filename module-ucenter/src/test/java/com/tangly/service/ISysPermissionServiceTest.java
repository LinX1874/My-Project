package com.tangly.service;

import com.tangly.UserCenterSpringTest;
import com.tangly.entity.SysPermission;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * date: 2018/5/17 17:06 <br/>
 *
 * @author Administrator
 * @since JDK 1.7
 */
public class ISysPermissionServiceTest extends UserCenterSpringTest {

    @Autowired
    private ISysPermissionService iSysPermissionService;

    @Test
    public void getPermissionList() {
        List<SysPermission> permissions = iSysPermissionService.getPermissionList(1L);
        System.out.println(permissions);
    }
}
package com.tangly.service.impl;

import com.tangly.base.BaseServiceImpl;
import com.tangly.entity.SysPermission;
import com.tangly.mapper.SysPermissionMapper;
import com.tangly.service.ISysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * date: 2018/5/17 16:17 <br/>
 *
 * @author Administrator
 * @since JDK 1.7
 */
@Service
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermission> implements ISysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public List<SysPermission> getPermissionList(Long userId) {
        return sysPermissionMapper.getPermission(userId);
    }
}

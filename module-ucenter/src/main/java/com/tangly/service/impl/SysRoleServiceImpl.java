package com.tangly.service.impl;

import com.tangly.base.BaseServiceImpl;
import com.tangly.entity.SysRole;
import com.tangly.mapper.SysRoleMapper;
import com.tangly.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * date: 2018/5/17 16:15 <br/>
 *
 * @author Administrator
 * @since JDK 1.7
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements ISysRoleService{

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> getSysRole(Long userId) {
        return sysRoleMapper.getSysRole(userId);
    }
}

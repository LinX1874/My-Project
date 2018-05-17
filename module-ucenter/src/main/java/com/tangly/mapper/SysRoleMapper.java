package com.tangly.mapper;

import com.tangly.base.BaseMybatisMapper;
import com.tangly.entity.SysRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleMapper extends BaseMybatisMapper<SysRole> {
    /**
     * 根据用户账号Id获取角色列表
     * @param userId
     * @return
     */
    List<SysRole> getSysRole(Long userId);
}
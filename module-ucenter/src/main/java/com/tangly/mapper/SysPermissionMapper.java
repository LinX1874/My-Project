package com.tangly.mapper;

import com.tangly.base.BaseMybatisMapper;
import com.tangly.entity.SysPermission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tangly
 */
@Repository
public interface SysPermissionMapper extends BaseMybatisMapper<SysPermission> {

    /**
     * 根据用户ID获取权限列表
     * @param userId
     * @return
     */
    List<SysPermission> getPermission(Long userId);
}
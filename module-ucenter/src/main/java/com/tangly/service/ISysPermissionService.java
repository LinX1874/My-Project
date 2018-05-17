package com.tangly.service;

import com.tangly.base.IBaseService;
import com.tangly.entity.SysPermission;

import java.util.List;

/**
 * date: 2018/1/2 17:37 <br/>
 *
 * @author tangly
 * @since JDK 1.7
 */
public interface ISysPermissionService extends IBaseService<SysPermission> {

    /**
     * 根据用户id获取权限列表
     * @param userId
     * @return
     */
    List<SysPermission> getPermissionList(Long userId);
}

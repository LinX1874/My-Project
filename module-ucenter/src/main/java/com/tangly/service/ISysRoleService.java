package com.tangly.service;

import com.tangly.base.IBaseService;
import com.tangly.entity.SysRole;

import java.util.List;

/**
 * date: 2018/1/2 17:37 <br/>
 *
 * @author tangly
 * @since JDK 1.7
 */
public interface ISysRoleService extends IBaseService<SysRole> {

    /**
     * 根据用户id获取相应角色列表
     * @param userId
     * @return
     */
    List<SysRole> getSysRole(Long userId);

}

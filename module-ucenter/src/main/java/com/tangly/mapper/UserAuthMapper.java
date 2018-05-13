package com.tangly.mapper;

import com.tangly.base.BaseMybatisMapper;
import com.tangly.entity.UserAuth;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthMapper extends BaseMybatisMapper<UserAuth> {

    /**
     * 根据用户登录账号获取用户信息
     * @param loginAccount
     * @return
     */
    UserAuth getUserAuth(String loginAccount);

}
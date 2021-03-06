package com.tangly.service.impl;

import com.tangly.base.BaseServiceImpl;
import com.tangly.entity.UserAuth;
import com.tangly.entity.UserInfo;
import com.tangly.mapper.UserAuthMapper;
import com.tangly.mapper.UserInfoMapper;
import com.tangly.service.IUserAuthService;
import com.tangly.service.IUserInfoService;
import com.tangly.util.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * date: 2018/5/2 10:24 <br/>
 *
 * @author tangly
 * @since JDK 1.7
 */
@Service
public class UserAuthServiceImpl extends BaseServiceImpl<UserAuth> implements IUserAuthService {

    @Autowired
    UserAuthMapper userAuthMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    PasswordHelper passwordHelper;

    @Autowired
    IUserInfoService iUserInfoService;

    @Override
    public UserAuth getUserAuth(String loginAccount) {
        return userAuthMapper.getUserAuth(loginAccount);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int registerUserAuth(UserAuth userAuth) {
        passwordHelper.encryptNewPassForUser(userAuth);

        UserInfo ui = iUserInfoService.insert(userAuth.getUserInfo());

        userAuth.setUserInfo(ui);
        userAuth.setUserInfoId(ui.getId());
        userAuth.setCreateTime(new Date());

        return  userAuthMapper.insertSelective(userAuth);
    }

    @Override
    public boolean existUserName(String loginAccount) {
        Example example = new Example(UserAuth.class);
        example.or().andEqualTo("loginAccount",loginAccount);
        if (mapper.selectCountByExample(example) > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void save(UserAuth userAuth) {
        mapper.insert(userAuth);
    }

    @Override
    public void updateLoginInfoFail(UserAuth userAuth, String requestIP) {
        userAuth.setLastLoginTryIp(requestIP);
        userAuth.setLastLoginTryCount(userAuth.getLastLoginTryCount() + 1);
        updateByPrimaryKeySelective(userAuth);
    }

    @Override
    public void updateLoginInfoSuccess(UserAuth userAuth, String requestIP, String token) {
        userAuth.setLastLoginTime(new Date());
        userAuth.setLastLoginToken(token);
        userAuth.setLastLoginIp(requestIP);
        userAuth.setLastLoginTryCount(0);
        updateByPrimaryKeySelective(userAuth);
    }
}

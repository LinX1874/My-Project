package com.tangly.service;


import com.tangly.base.IBaseService;
import com.tangly.entity.UserAuth;

/**
 * date: 2018/5/2 10:23 <br/>
 * 用户账号接口类
 * @author tangly
 * @since JDK 1.7
 */
public interface IUserAuthService extends IBaseService<UserAuth> {

    /**
     * 根据用户登录账号取账号登录实体
     * @param loginAccount
     * @return
     */
    UserAuth getUserAuth(String loginAccount);

    /**
     * 注册用户
     * @param userAuth
     */
    int registerUserAuth(UserAuth userAuth);

    /**
     * 检验用户名是否存在
     * @param loginAccount
     * @return
     */
    boolean existUserName(String loginAccount);

    /**
     * 保存用户
     * @param userAuth
     */
    void save(UserAuth userAuth);

    /**
     * 记录最后一次登录失败
     * @param userAuth
     * @param requestIP
     */
    void updateLoginInfoFail(UserAuth userAuth, String requestIP);

    /**
     * 更新最新一次登录成功
     * @param userAuth
     * @param requestIP
     * @param token
     */
    void updateLoginInfoSuccess(UserAuth userAuth, String requestIP, String token);
}

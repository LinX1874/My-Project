package com.tangly.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_auth")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class UserAuth {

    @Transient private UserInfo userInfo;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 关联用户信息
     */
    @Column(name = "user_info_id")
    private Long userInfoId;

    /**
     * 是否可用
     */
    @Column(name = "auth_available")
    private Boolean authAvailable;

    /**
     * 账号类型，用户名密码
     */
    @Column(name = "login_type")
    private String loginType;

    /**
     * 登录账号
     */
    @Column(name = "login_account")
    private String loginAccount;

    /**
     * 密码
     */
    @Column(name = "login_password")
    private String loginPassword;

    /**
     * 密码加盐
     */
    @Column(name = "login_salt")
    private String loginSalt;

    /**
     * 最后登录ip地址
     */
    @Column(name = "last_login_ip")
    private String lastLoginIp;

    /**
     * 最后登录时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 最后登录的设备
     */
    @Column(name = "last_login_device")
    private String lastLoginDevice;

    /**
     * 最后一次登录尝试
     */
    @Column(name = "last_login_try_count")
    private Integer lastLoginTryCount;

    /**
     * 上次登录生成的token
     */
    @Column(name = "last_login_token")
    private String lastLoginToken;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取关联用户信息
     *
     * @return user_info_id - 关联用户信息
     */
    public Long getUserInfoId() {
        return userInfoId;
    }

    /**
     * 设置关联用户信息
     *
     * @param userInfoId 关联用户信息
     */
    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    /**
     * 获取是否可用
     *
     * @return auth_available - 是否可用
     */
    public Boolean getAuthAvailable() {
        return authAvailable;
    }

    /**
     * 设置是否可用
     *
     * @param authAvailable 是否可用
     */
    public void setAuthAvailable(Boolean authAvailable) {
        this.authAvailable = authAvailable;
    }

    /**
     * 获取账号类型，用户名密码
     *
     * @return login_type - 账号类型，用户名密码
     */
    public String getLoginType() {
        return loginType;
    }

    /**
     * 设置账号类型，用户名密码
     *
     * @param loginType 账号类型，用户名密码
     */
    public void setLoginType(String loginType) {
        this.loginType = loginType == null ? null : loginType.trim();
    }

    /**
     * 获取登录账号
     *
     * @return login_account - 登录账号
     */
    public String getLoginAccount() {
        return loginAccount;
    }

    /**
     * 设置登录账号
     *
     * @param loginAccount 登录账号
     */
    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount == null ? null : loginAccount.trim();
    }

    /**
     * 获取密码
     *
     * @return login_password - 密码
     */
    public String getLoginPassword() {
        return loginPassword;
    }

    /**
     * 设置密码
     *
     * @param loginPassword 密码
     */
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword == null ? null : loginPassword.trim();
    }

    /**
     * 获取密码加盐
     *
     * @return login_salt - 密码加盐
     */
    public String getLoginSalt() {
        return loginSalt;
    }

    /**
     * 设置密码加盐
     *
     * @param loginSalt 密码加盐
     */
    public void setLoginSalt(String loginSalt) {
        this.loginSalt = loginSalt == null ? null : loginSalt.trim();
    }

    /**
     * 获取最后登录ip地址
     *
     * @return last_login_ip - 最后登录ip地址
     */
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    /**
     * 设置最后登录ip地址
     *
     * @param lastLoginIp 最后登录ip地址
     */
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    /**
     * 获取最后登录时间
     *
     * @return last_login_time - 最后登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置最后登录时间
     *
     * @param lastLoginTime 最后登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取最后登录的设备
     *
     * @return last_login_device - 最后登录的设备
     */
    public String getLastLoginDevice() {
        return lastLoginDevice;
    }

    /**
     * 设置最后登录的设备
     *
     * @param lastLoginDevice 最后登录的设备
     */
    public void setLastLoginDevice(String lastLoginDevice) {
        this.lastLoginDevice = lastLoginDevice == null ? null : lastLoginDevice.trim();
    }

    /**
     * 获取最后一次登录尝试
     *
     * @return last_login_try_count - 最后一次登录尝试
     */
    public Integer getLastLoginTryCount() {
        return lastLoginTryCount;
    }

    /**
     * 设置最后一次登录尝试
     *
     * @param lastLoginTryCount 最后一次登录尝试
     */
    public void setLastLoginTryCount(Integer lastLoginTryCount) {
        this.lastLoginTryCount = lastLoginTryCount;
    }

    /**
     * 获取上次登录生成的token
     *
     * @return last_login_token - 上次登录生成的token
     */
    public String getLastLoginToken() {
        return lastLoginToken;
    }

    /**
     * 设置上次登录生成的token
     *
     * @param lastLoginToken 上次登录生成的token
     */
    public void setLastLoginToken(String lastLoginToken) {
        this.lastLoginToken = lastLoginToken == null ? null : lastLoginToken.trim();
    }
}
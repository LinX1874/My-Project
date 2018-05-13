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
    /**
     * 账号关联的用户信息实体
     */
    @Transient
    private UserInfo userInfo;

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

}
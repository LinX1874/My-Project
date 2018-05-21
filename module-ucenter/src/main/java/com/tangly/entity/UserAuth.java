package com.tangly.entity;

import com.tangly.enums.ELoginType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author tangly
 */
@Table(name = "user_auth")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "用户账号实体")
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
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Long id;

    /**
     * 关联用户信息
     */
    @Column(name = "user_info_id")
    @ApiModelProperty(hidden = true)
    private Long userInfoId;

    /**
     * 是否可用
     */
    @Column(name = "auth_available")
    @ApiModelProperty(notes = "是否可用", example = "true")
    private Boolean authAvailable;

    /**
     * 账号类型，用户名密码
     */
    @Column(name = "login_type")
    @ApiModelProperty( notes = "登录类型枚举，表示登录的途径方式")
    private ELoginType loginType;

    public void setLoginType(String label){
        this.loginType = ELoginType.enumOf(label);
    }

    /**
     * 登录账号
     */
    @Column(name = "login_account")
    @ApiModelProperty(example = "test", notes = "登录账号")
    private String loginAccount;

    /**
     * 密码
     */
    @Column(name = "login_password")
    @ApiModelProperty(example = "test", notes = "登录密码")
    private String loginPassword;

    /**
     * 密码加盐
     */
    @Column(name = "login_salt")
    @ApiModelProperty(hidden = true)
    private String loginSalt;

    /**
     * 最后登录ip地址
     */
    @Column(name = "last_login_ip")
    @ApiModelProperty(hidden = true)
    private String lastLoginIp;

    /**
     * 最后登录时间
     */
    @Column(name = "last_login_time")
    @ApiModelProperty(hidden = true)
    private Date lastLoginTime;

    /**
     * 最后登录的设备
     */
    @Column(name = "last_login_device")
    @ApiModelProperty(hidden = true)
    private String lastLoginDevice;

    /**
     * 最后一次登录尝试IP
     */
    @Column(name = "last_login_try_ip")
    @ApiModelProperty(hidden = true)
    private String lastLoginTryIp;

    /**
     * 最后一次登录尝试次数
     */
    @Column(name = "last_login_try_count")
    @ApiModelProperty(hidden = true)
    private Integer lastLoginTryCount;

    /**
     * 上次登录生成的token
     */
    @Column(name = "last_login_token")
    @ApiModelProperty(hidden = true)
    private String lastLoginToken;

    @Column(name = "create_time")
    @ApiModelProperty(hidden = true)
    private Date createTime;

    @Column(name = "update_time")
    @ApiModelProperty(hidden = true)
    private Date updateTime;

}
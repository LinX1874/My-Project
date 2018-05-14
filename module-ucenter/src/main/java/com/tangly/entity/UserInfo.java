package com.tangly.entity;

import com.tangly.enums.EUserSex;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author tanglye
 */
@Table(name = "user_info")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Long id;

    /**
     * 用户昵称
     */
    @Column(name = "user_nickname")
    @ApiModelProperty(notes = "昵称" , example = "张三")
    private String userNickname;

    /**
     * 用户头像
     */
    @Column(name = "user_avatar")
    @ApiModelProperty(notes = "头像地址", example = "上传的头像地址")
    private String userAvatar;

    /**
     * 性别
     */
    @Column(name = "user_sex")
    @ApiModelProperty(notes = "性别枚举类： 0：女 1 男 2：保密 - 传int不要传字符串", example = "0")
    private EUserSex userSex;

}
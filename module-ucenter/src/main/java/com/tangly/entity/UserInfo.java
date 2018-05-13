package com.tangly.entity;

import com.tangly.enums.EUserSex;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private String userNickname;

    /**
     * 用户头像
     */
    @Column(name = "user_avatar")
    private String userAvatar;

    /**
     * 性别
     */
    @Column(name = "user_sex")
    @ApiModelProperty(notes = "枚举类： 0：女 1 男 2：保密 - 传int不要传字符串", example = "0")
    private EUserSex userSex;

}
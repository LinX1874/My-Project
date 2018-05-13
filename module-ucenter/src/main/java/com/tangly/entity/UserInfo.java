package com.tangly.entity;

import com.tangly.enums.EUserSex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "user_info")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * 性别 0女 1男 2保密
     */
    @Column(name = "user_sex")
    private EUserSex eUserSex;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户昵称
     *
     * @return user_nickname - 用户昵称
     */
    public String getUserNickname() {
        return userNickname;
    }

    /**
     * 设置用户昵称
     *
     * @param userNickname 用户昵称
     */
    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname == null ? null : userNickname.trim();
    }

    /**
     * 获取用户头像
     *
     * @return user_avatar - 用户头像
     */
    public String getUserAvatar() {
        return userAvatar;
    }

    /**
     * 设置用户头像
     *
     * @param userAvatar 用户头像
     */
    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar == null ? null : userAvatar.trim();
    }

    /**
     * 获取性别 0女 1男 2保密
     *
     * @return user_sex - 性别 0女 1男 2保密
     */
    public EUserSex geteUserSex() {
        return eUserSex;
    }

    /**
     * 设置性别 0女 1男 2保密
     *
     * @param eUserSex 性别 0女 1男 2保密
     */
    public void seteUserSex(EUserSex eUserSex) {
        this.eUserSex = eUserSex;
    }
}
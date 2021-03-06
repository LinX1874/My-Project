package com.tangly.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.tangly.enums.EUserSex;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    @NotNull
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
    @ApiModelProperty(notes = "性别")
    @NotNull
    private EUserSex userSex;

    @JSONField(name ="user_sex")
    public void setUserSex(String label){
        this.userSex = EUserSex.enumOf(label);
    }

}
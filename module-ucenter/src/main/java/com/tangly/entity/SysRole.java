package com.tangly.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sys_role")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "用户角色")
public class SysRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean available;

    @ApiModelProperty(value = "角色描述")
    private String description;

    @ApiModelProperty(value = "角色名")
    private String name;

    @ApiModelProperty(value = "角色等级，0是最高级")
    private Integer level;

}
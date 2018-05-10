package com.tangly.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author tangly
 */
@Entity
@Data
public class HelloWorld {

    @Id
    @ApiModelProperty(value = "主键",required = true)
    private Integer id;

    @ApiModelProperty(value = "名称")
    @NotNull(message = "名字不能为空")
    @Size(min = 2, max = 14 , message = "名字长度限制为2-14")
    private String name;

    @Pattern(regexp="\\d{11}" , message = "手机号格式不正确")
    private String phoneNum;

    public HelloWorld() {
    }

    public HelloWorld(String name) {
        this.name = name;
    }

    public HelloWorld(Integer id , String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HelloWolrd{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}
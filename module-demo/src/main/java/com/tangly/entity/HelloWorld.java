package com.tangly.entity;

import com.tangly.enums.EType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * 【@Data @Builder @NoArgsConstructor @AllArgsConstructor】 所有的实体entity请加入这几个注解；
 * 以上注解说明详见： 【Lombok注解】  https://blog.csdn.net/sunsfan/article/details/53542374
 * 在方法中不要写setter/getter/toString等冗余代码，而使用lombok的该注解代替
 *
 * 【@Builder @NoArgsConstructor @AllArgsConstructor】请尽量避免使用对象的 new XX() 构造方法， 而使用Lombok注解@Builder的构造器方式
 * 【@ApiModel @ApiModelProperty】 是SwaggerUI的接口文档注解，详见： https://blog.csdn.net/xupeng874395012/article/details/68946676
 */
@Entity
@Data @Builder @NoArgsConstructor @AllArgsConstructor
@ApiModel(description = "示例实体")
public class HelloWorld {

    @Id
    @ApiModelProperty(value = "主键",required = true)
    /**
     *  配置自增 @GeneratedValue，
     *  实体insert后可以立即用getId方法获得
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "名称")
    /**
     * @NotNull @Pattern @Min @Max 是javax.validation.contrains校验框架中的注解；
     *
     */
    @NotNull(message = "名字不能为空")
    @Size(min = 2, max = 14 , message = "名字长度限制为2-14")
    private String name;

    @ApiModelProperty(value = "手机号")
    @Pattern(regexp="\\d{11}" , message = "手机号格式不正确")
    private String phoneNum;

    /**
     * 枚举类示例
     * 有限范围的分类请用枚举；
     */
    @Column(name = "type")
    private EType type;

}
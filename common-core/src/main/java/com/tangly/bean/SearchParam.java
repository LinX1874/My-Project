package com.tangly.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * date: 2018/5/10 15:44 <br/>
 *
 * @author tangly
 * @since JDK 1.7
 */
@Data
@ApiModel(description = "查询参数封装类")
public class SearchParam {

    /**
     * 排序条件
     */
    @ApiModelProperty(value = "排序条件 例：{id:'asc',name:'desc'} 表示根据id 正序排列并根据name逆向排序;")
    private Map<String,Object> orderBys;

    /**
     * 搜索条件
     */
    @ApiModelProperty(value ="字段过滤条件 例：name:'张三',phone_num:'139%'} 表示 name等于张三 且 phone是以139开头的。")
    private Map<String, Object> columParams;

}

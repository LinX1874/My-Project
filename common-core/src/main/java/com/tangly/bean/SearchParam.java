package com.tangly.bean;

import com.tangly.enums.ESort;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
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


    @ApiModelProperty(value = "页码", example = "1")
    private Integer page;

    @ApiModelProperty(value = "页面大小", example = "10")
    private Integer size;

    /**
     * 排序条件
     */
    @ApiModelProperty(value = "排序条件 例：{id:'asc',name:'desc'} 表示根据id 正序排列并根据name逆向排序;")
    private Map<String, ESort> orderBys;

    /**
     * 搜索条件
     */
    @ApiModelProperty(value = "字段过滤条件 例：name:'张三',phone_num:'139%'} 表示 name等于张三 且 phone是以139开头的。")
    private Map<String, Object> columnParams;

    /**
     * 添加排序条件
     * @param column
     * @param sort
     */
    public void addOrderBy(String column , ESort sort){
        if(ObjectUtils.isEmpty(sort)){
            return;
        }
        if(ObjectUtils.isEmpty(this.orderBys)){
            this.orderBys = new HashMap<>(1);
        }
        this.orderBys.put(column,sort);
    }

    /**
     * 添加搜索条件
     * @param column
     * @param value
     */
    public void addColumn(String column , String value){
        if(ObjectUtils.isEmpty(value)){
            return;
        }
        if(ObjectUtils.isEmpty(this.columnParams)){
            this.columnParams = new HashMap<>(1);
        }
        this.columnParams.put(column,value);
    }

}

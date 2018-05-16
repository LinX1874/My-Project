package com.tangly.bean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.lang.reflect.ParameterizedType;
import java.util.Map;

/**
 * date: 2018/5/10 15:44 <br/>
 *
 * @author tangly
 * @since JDK 1.7
 */
@Data
@ApiModel(description = "分页查询请求类")
public class PageRequest{

    /***
     * 当前页码
     */
    @ApiModelProperty(value = "页码",example = "1")
    private int pageNum;

    /**
     * 页面大小
     */
    @ApiModelProperty(value = "页面大小",example = "10")
    private int pageSize;

    /**
     * 排序条件
     */
    @ApiModelProperty(value = "排序条件 例：{id:'asc',name:'desc'} 表示根据id 正序排列并根据name逆向排序;")
    private Map<String,Object> orderBys;

    /**
     * 搜索条件
     */
    @ApiModelProperty(value ="字段过滤条件 例：name:'张三',phone_num:'139%'} 表示 name等于张三 且 phone是以139开头的。")
    private Map<String, Object> searchParams;

    public PageRequest(){
        super();
    }

    public PageRequest(PageRequest request) {
        this.pageNum = request.getPageNum();
        this.pageSize = request.getPageSize();
        this.orderBys = request.getOrderBys();
        this.searchParams = request.getSearchParams();
    }
}

package com.tangly.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * date: 2018/5/10 15:44 <br/>
 *
 * @author Administrator
 * @since JDK 1.7
 */
@Data
@ApiModel(value = "分页查询请求类")
public class PageRequest implements Serializable {

    /***
     * 当前页码
     */
    private int pageNum;

    /**
     * 页面大小
     */
    private int pageSize;

    /**
     * 排序条件
     */
    private Map<String,Object> orderBys;

    /**
     * 搜索条件
     */
    private Map<String, Object> searchParams;

    @ApiModelProperty(hidden = true)
    private Class clazz;

    public PageRequest(){super();}


    public PageRequest(Class clazz,PageRequest request) {
        this.clazz = clazz;
        this.pageNum = request.getPageNum();
        this.pageSize = request.getPageSize();
        this.orderBys = request.getOrderBys();
        this.searchParams = request.getSearchParams();
    }
}

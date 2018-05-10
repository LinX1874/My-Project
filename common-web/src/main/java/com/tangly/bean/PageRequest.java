package com.tangly.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * date: 2018/5/10 15:44 <br/>
 *
 * @author Administrator
 * @since JDK 1.7
 */
@Data
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
    private List<Param> sorts;

    /**
     * 搜索条件
     */
    private List<Param> searchParams;

    @ApiModelProperty(hidden = true)
    private Class clazz;

    public PageRequest(){}

    public PageRequest(Class clazz,PageRequest request) {
        this.clazz = clazz;
        this.pageNum = request.getPageNum();
        this.pageSize = request.getPageSize();
        this.sorts = request.getSorts();
        this.searchParams = request.getSearchParams();
    }
}

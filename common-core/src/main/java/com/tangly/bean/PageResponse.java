package com.tangly.bean;

import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author tangly
 */
@ApiModel(description = "分页返回")
@Data
public class PageResponse<T> implements Serializable {

    @ApiModelProperty(hidden = true)
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "页码")
    private int pageNum;

    @ApiModelProperty(value = "页面大小")
    private int pageSize;

    @ApiModelProperty(value = "当前页面条数")
    private int size;

    @ApiModelProperty(value = "总记录数")
    private long total;

    @ApiModelProperty(value = "总页数")
    private int pages;

    @ApiModelProperty(value = "数据列表")
    private List<T> list;

    @ApiModelProperty(value = "当前是否第一页")
    private boolean firstPage;

    @ApiModelProperty(value = "当前是否最后一页")
    private boolean lastPage;

    public PageResponse() {
        this.firstPage = false;
        this.lastPage = false;
    }

    public PageResponse(List<T> list) {
        this(list, 10);
    }

    public PageResponse(List<T> list, int navigatePages) {
        this.firstPage = false;
        this.lastPage = false;
        if (list instanceof Page) {
            Page page = (Page) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.pages = page.getPages();
            this.list = page;
            this.size = page.size();
            this.total = page.getTotal();
        } else if (list != null) {
            this.pageNum = 1;
            this.pageSize = list.size();
            this.pages = this.pageSize > 0 ? 1 : 0;
            this.list = list;
            this.size = list.size();
            this.total = (long) list.size();
        }
    }

}

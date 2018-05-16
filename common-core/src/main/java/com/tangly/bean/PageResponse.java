package com.tangly.bean;

import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

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

    @ApiModelProperty(value = "起始记录号")
    private int startRow;

    @ApiModelProperty(value = "结束记录序号")
    private int endRow;

    @ApiModelProperty(value = "总记录数")
    private long total;

    @ApiModelProperty(value = "总页数")
    private int pages;

    @ApiModelProperty(value = "数据列表")
    private List<T> list;
    @ApiModelProperty(value = "上一页页码")
    private int prePage;

    @ApiModelProperty(value = "下一页页码")
    private int nextPage;

    @ApiModelProperty(value = "当前是否第一页")
    private boolean firstPage;

    @ApiModelProperty(value = "当前是否最后一页")
    private boolean lastPage;

    @ApiModelProperty(value = "是否还有上一页")
    private boolean hasPreviousPage;

    @ApiModelProperty(value = "是否还有下一页")
    private boolean hasNextPage;

    @ApiModelProperty(value = "分页导航显示数量")
    private int navigatePages;

    @ApiModelProperty(value = "分页导航数组")
    private int[] navigatepageNums;

    @ApiModelProperty(value = "分页导航首页页码")
    private int navigateFirstPage;

    @ApiModelProperty(value = "分页导航末页页码")
    private int navigateLastPage;

    public PageResponse() {
        this.firstPage = false;
        this.lastPage = false;
        this.hasPreviousPage = false;
        this.hasNextPage = false;
    }

    public PageResponse(List<T> list) {
        this(list, 10);
    }

    public PageResponse(List<T> list, int navigatePages) {
        this.firstPage = false;
        this.lastPage = false;
        this.hasPreviousPage = false;
        this.hasNextPage = false;
        if (list instanceof Page) {
            Page page = (Page)list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.pages = page.getPages();
            this.list = page;
            this.size = page.size();
            this.total = page.getTotal();
            if (this.size == 0) {
                this.startRow = 0;
                this.endRow = 0;
            } else {
                this.startRow = page.getStartRow() + 1;
                this.endRow = this.startRow - 1 + this.size;
            }
        } else if (list != null) {
            this.pageNum = 1;
            this.pageSize = list.size();
            this.pages = this.pageSize > 0 ? 1 : 0;
            this.list = list;
            this.size = list.size();
            this.total = (long)list.size();
            this.startRow = 0;
            this.endRow = list.size() > 0 ? list.size() - 1 : 0;
        }

        if (list != null) {
            this.navigatePages = navigatePages;
            this.calcNavigatePageNum();
            this.calcPage();
            this.judgePageBoudary();
        }

    }

    private void calcNavigatePageNum() {
        int i;
        if (this.pages <= this.navigatePages) {
            this.navigatepageNums = new int[this.pages];

            for(i = 0; i < this.pages; ++i) {
                this.navigatepageNums[i] = i + 1;
            }
        } else {
            this.navigatepageNums = new int[this.navigatePages];
            i = this.pageNum - this.navigatePages / 2;
            int endNum = this.pageNum + this.navigatePages / 2;
            if (i < 1) {
                i = 1;

                for(i = 0; i < this.navigatePages; ++i) {
                    this.navigatepageNums[i] = i++;
                }
            } else if (endNum > this.pages) {
                endNum = this.pages;

                for(i = this.navigatePages - 1; i >= 0; --i) {
                    this.navigatepageNums[i] = endNum--;
                }
            } else {
                for(i = 0; i < this.navigatePages; ++i) {
                    this.navigatepageNums[i] = i++;
                }
            }
        }

    }

    private void calcPage() {
        if (this.navigatepageNums != null && this.navigatepageNums.length > 0) {
            this.navigateFirstPage = this.navigatepageNums[0];
            this.navigateLastPage = this.navigatepageNums[this.navigatepageNums.length - 1];
            if (this.pageNum > 1) {
                this.prePage = this.pageNum - 1;
            }

            if (this.pageNum < this.pages) {
                this.nextPage = this.pageNum + 1;
            }
        }

    }

    private void judgePageBoudary() {
        this.firstPage = this.pageNum == 1;
        this.lastPage = this.pageNum == this.pages || this.pages == 0;
        this.hasPreviousPage = this.pageNum > 1;
        this.hasNextPage = this.pageNum < this.pages;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("PageResponse{");
        sb.append("pageNum=").append(this.pageNum);
        sb.append(", pageSize=").append(this.pageSize);
        sb.append(", size=").append(this.size);
        sb.append(", startRow=").append(this.startRow);
        sb.append(", endRow=").append(this.endRow);
        sb.append(", total=").append(this.total);
        sb.append(", pages=").append(this.pages);
        sb.append(", list=").append(this.list);
        sb.append(", prePage=").append(this.prePage);
        sb.append(", nextPage=").append(this.nextPage);
        sb.append(", firstPage=").append(this.firstPage);
        sb.append(", lastPage=").append(this.lastPage);
        sb.append(", hasPreviousPage=").append(this.hasPreviousPage);
        sb.append(", hasNextPage=").append(this.hasNextPage);
        sb.append(", navigatePages=").append(this.navigatePages);
        sb.append(", navigateFirstPage=").append(this.navigateFirstPage);
        sb.append(", navigateLastPage=").append(this.navigateLastPage);
        sb.append(", navigatepageNums=");
        if (this.navigatepageNums == null) {
            sb.append("null");
        } else {
            sb.append('[');

            for(int i = 0; i < this.navigatepageNums.length; ++i) {
                sb.append(i == 0 ? "" : ", ").append(this.navigatepageNums[i]);
            }

            sb.append(']');
        }

        sb.append('}');
        return sb.toString();
    }
}

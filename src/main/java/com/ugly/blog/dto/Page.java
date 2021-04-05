package com.ugly.blog.dto;

import java.util.List;

/**
 * @author JwZheng
 */
public class Page<T> {
    private Integer pageNo;
    /**
     * 总页数
     */
    private Integer totalPage;
    private Integer pageSize = 4;
    /**
     * 总的item数
     */
    private Integer totalCount;
    private List<T> items;

    public Page() {
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + totalPage +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + totalCount +
                ", items=" + items +
                '}';
    }
}

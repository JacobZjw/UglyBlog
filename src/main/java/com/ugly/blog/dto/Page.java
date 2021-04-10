package com.ugly.blog.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author JwZheng
 */
@Data
public class Page<T> implements Serializable {
    private static final long serialVersionUID = -38888009832291499L;
    /**
     * 当前页码
     */
    private Integer pageNo;

    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 每页大小，默认值为4
     */
    private Integer pageSize = 4;

    /**
     * 总数据数
     */
    private Integer totalCount;

    /**
     * 页面数据列表
     */
    private List<T> items;
}

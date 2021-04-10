package com.ugly.blog.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/2 16:20
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Article implements Serializable {

    private static final long serialVersionUID = 1426367570761539L;

    private Integer articleId;

    private Integer authorId;

    private String title;

    private String content;

    private Integer viewCount;

    private Integer commentCount;

    private Integer likeCount;

    private Integer status;

    private Integer order;

    private Date createTime;

    private Date updateTime;

    private String summary;

    /**
     * 是否转载文章(0是原创 1是转载)
     */
    private Integer isReprint;


    /**
     * 作者信息，非数据库字段
     */
    @JsonUnwrapped
    private User user;

    /**
     * 标签信息，非数据库字段
     */
    @JsonInclude
    private List<Tag> tagList;

    /**
     * 分类信息，非数据库字段
     */
    @JsonInclude
    private List<Category> categoryList;

}
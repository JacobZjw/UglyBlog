package com.ugly.blog.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/2 16:20
 */
@Data
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
     * 是否转载文章
     */
    private Integer isReprint;

    private User user;

    private List<Tag> tagList;

    private List<Category> categoryList;

}
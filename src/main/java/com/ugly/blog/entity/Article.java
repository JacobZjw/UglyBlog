package com.ugly.blog.entity;

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

    private Integer articleUserId;

    private String articleTitle;

    private String articleContent;

    private Integer articleViewCount;

    private Integer articleCommentCount;

    private Integer articleLikeCount;

    private Integer articleStatus;

    private Integer articleOrder;

    private Date articleCreateTime;

    private Date articleUpdateTime;

    private String articleSummary;

    /**
     * 是否转载文章
     */
    private Integer articleIsReprint;

    private User user;

    private List<Tag> tagList;

    private List<Category> categoryList;

}
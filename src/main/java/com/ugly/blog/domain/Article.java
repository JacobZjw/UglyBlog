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

    private Integer categoryId;

    /**
     * 标题
     */
    private String title;

    /**
     * 封面图
     */
    private String coverImage;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 关键词
     */
    private String keyWords;

    /**
     * 文章内容
     */
    private String content;

    /**
     * markdown源码
     */
    private String contentMd;

    /**
     * 点击量
     */
    private Integer clickCount;

    /**
     * 评论数
     */
    private Integer commentCount;

    /**
     * 点赞数
     */
    private Integer likeCount;

    /**
     * 是否置顶 1是 0否
     */
    private Integer isTop;

    /**
     * 是否显示 1是 0否
     */
    private Integer isShow;

    /**
     * 是否原创 1是 0否
     */
    private Integer isOriginal;

    /**
     * 是否发布 1是 0否
     */
    private Integer isPublished;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 作者信息，非数据库字段
     */
    @JsonUnwrapped
    private User user;

    /**
     * 标签信息，非数据库字段
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Tag> tagList;

    /**
     * 分类信息，非数据库字段
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonUnwrapped
    private Category category;

}
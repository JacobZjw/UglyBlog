package com.ugly.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 文章分类关联表
 *
 * @author liuyanzhao
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCategoryRef implements Serializable{

    private static final long serialVersionUID = -6809206515467725995L;

    private Integer articleId;

    private Integer categoryId;

}
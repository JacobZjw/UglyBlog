package com.ugly.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 文章和标签关联
 *
 * @author JwZheng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleTagRef implements Serializable {
    private static final long serialVersionUID = -5816783232020910492L;

    private Integer articleId;

    private Integer tagId;

}

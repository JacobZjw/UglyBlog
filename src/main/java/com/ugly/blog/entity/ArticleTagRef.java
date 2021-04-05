package com.ugly.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 文章和标签关联
 *
 * @author 言曌
 * @date 2018/11/17 下午5:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleTagRef implements Serializable {
    private static final long serialVersionUID = -5816783232020910492L;

    private Integer articleId;

    private Integer tagId;


}

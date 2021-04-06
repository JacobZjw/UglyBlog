package com.ugly.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JwZheng
 * @date 2021/4/6 17:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrevAndNextArticle {
    private Integer prevArticleId;
    private String prevArticleTitle;

    private Integer nextArticleId;
    private String nextArticleTitle;
}

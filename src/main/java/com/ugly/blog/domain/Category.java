package com.ugly.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JwZheng
 * @date 2021/4/2 16:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private static final long serialVersionUID = -5235625432731385L;

    private Integer categoryId;

    private String categoryName;
}

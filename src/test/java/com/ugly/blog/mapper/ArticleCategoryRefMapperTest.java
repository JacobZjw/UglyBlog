package com.ugly.blog.mapper;

import com.ugly.blog.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author JwZheng
 * @date 2021/4/10 16:04
 */
public class ArticleCategoryRefMapperTest extends BaseTest {

    @Autowired
    private ArticleCategoryRefMapper articleCategoryRefMapper;

    @Test
    public void getCategoryListByArticleId() {
        articleCategoryRefMapper.getCategoryListByArticleId(1).forEach(System.out::println);
    }
}
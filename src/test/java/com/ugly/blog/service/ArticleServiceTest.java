package com.ugly.blog.service;

import com.ugly.blog.BaseTest;
import com.ugly.blog.domain.Article;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/5 16:05
 */
public class ArticleServiceTest extends BaseTest {

    @Autowired
    private ArticleService articleService;

    @Test
    public void getArticleById() {
    }

    @Test
    public void getTopHotArticle() {
        List<Article> list = articleService.getTopHotArticle(4);
        Assert.assertEquals(list.size(), 4);
    }

    @Test
    public void getCount() {
    }
}
package com.ugly.blog.mapper;

import com.ugly.blog.BaseTest;
import com.ugly.blog.entity.Article;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/2 18:56
 */
public class ArticleMapperTest extends BaseTest {

    @Autowired
    ArticleMapper articleMapper;

    @Test
    public void getById() {
        System.out.println(articleMapper.getById(2));
    }

    @Test
    public void getTopHotArticle() {
        List<Article> list = articleMapper.getTopHotArticle(4);
        Assert.assertEquals(list.size(), 4);
    }

    @Test
    public void getCount() {
        System.out.println(articleMapper.getCount());
    }

    @Test
    public void testGetById() {
    }

    @Test
    public void testGetCount() {
    }

    @Test
    public void getPage() {
        articleMapper.getPage(1, 5).forEach(System.out::println);
    }

    @Test
    public void getPageByTag() {
    }

    @Test
    public void getPageByCategory() {
    }
}
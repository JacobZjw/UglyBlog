package com.ugly.blog.mapper;

import com.ugly.blog.BaseTest;
import com.ugly.blog.domain.Article;
import com.ugly.blog.domain.User;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author JwZheng
 * @date 2021/4/9 16:12
 */
public class ArticleMapperTest extends BaseTest {

    @Autowired
    private ArticleMapper articleMapper;


    @Test
    public void addViewCount() {
        assertEquals(1, articleMapper.addLikeCount(1));
    }

    @Test
    public void addLikeCount() {
        assertEquals(1, articleMapper.addLikeCount(1));
    }

    @Test
    public void getCount() {
        assertNotEquals(0, articleMapper.getTotalCount());
    }

    @Test
    public void getById() {
        System.out.println(articleMapper.getFullInfoById(1));
        assertNotNull(articleMapper.getFullInfoById(1));
    }

    @Test
    public void getTopHotArticle() {
        List<Article> list = articleMapper.getTopHotArticle(4);
        assertEquals(list.size(), 4);
    }

    @Test
    public void getPage() {
//        List<Article> list = articleMapper.getPage(1, 4);
//        assertEquals(list.size(), 4);
        articleMapper.getListByCondition(null);
    }

    @Test
    public void getNextArticle() {
        assertNotNull(articleMapper.getNextArticle(1));
    }

    @Test
    public void getPrevArticle() {
        assertNotNull(articleMapper.getPrevArticle(2));
    }

    @Test
    public void updateArticle() {
        Article article = articleMapper.getFullInfoById(1);
        assertEquals(1, articleMapper.update(article));
    }

    @Test
    @Ignore
    public void insertArticle() {
        Article article = articleMapper.getFullInfoById(1);
        assertEquals(1, articleMapper.insert(article));
    }

    @Test
    @Ignore
    public void deleteArticle() {
        assertEquals(1, articleMapper.delete(7));
    }

    @Test
    public void getListByCondition() {
        Article article = new Article();
        User user = new User();
        user.setNickname("言曌");
        article.setTitle("Docker");
        article.setUser(user);
//        articleMapper.getListByCondition(1, 5, article).forEach(System.out::println);
    }
}
package com.ugly.blog.service.impl;

import com.ugly.blog.domain.Article;
import com.ugly.blog.mapper.ArticleMapper;
import com.ugly.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/5 15:56
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;

    @Autowired
    public ArticleServiceImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    public int insertArticle(Article article) {
        return articleMapper.insertArticle(article);
    }

    @Override
    public int updateArticle(Article article) {
        return articleMapper.updateArticle(article);
    }

    @Override
    public int deleteArticle(Integer articleId) {
        return articleMapper.deleteArticle(articleId);
    }

    @Override
    public int addViewCount(Integer articleId) {
        return articleMapper.addViewCount(articleId);
    }

    @Override
    public int addLikeCount(Integer articleId) {
        return articleMapper.addLikeCount(articleId);
    }

    @Override
    public Article getArticleById(int articleId) {
        return articleMapper.getById(articleId);
    }

    @Override
    public List<Article> getTopHotArticle(int num) {
        return articleMapper.getTopHotArticle(num);
    }

    @Override
    public int getCount() {
        return articleMapper.getCount();
    }

    @Override
    public Article getNextArticle(Integer articleId) {
        return articleMapper.getNextArticle(articleId);
    }

    @Override
    public Article getPrevArticle(Integer articleId) {
        return articleMapper.getPrevArticle(articleId);
    }
}

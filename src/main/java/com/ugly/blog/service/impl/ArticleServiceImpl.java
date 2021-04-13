package com.ugly.blog.service.impl;

import com.ugly.blog.domain.Article;
import com.ugly.blog.domain.ArticleCategoryRef;
import com.ugly.blog.domain.Tag;
import com.ugly.blog.mapper.ArticleCategoryRefMapper;
import com.ugly.blog.mapper.ArticleMapper;
import com.ugly.blog.mapper.ArticleTagRefMapper;
import com.ugly.blog.mapper.UserMapper;
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
    private final UserMapper userMapper;
    private final ArticleTagRefMapper atrMapper;
    private final ArticleCategoryRefMapper acrMapper;

    @Autowired
    public ArticleServiceImpl(ArticleMapper articleMapper, UserMapper userMapper, ArticleTagRefMapper atrMapper, ArticleCategoryRefMapper acrMapper) {
        this.articleMapper = articleMapper;
        this.userMapper = userMapper;
        this.atrMapper = atrMapper;
        this.acrMapper = acrMapper;
    }

    @Override
    public int insert(Article article) {
        if (articleMapper.insert(article) > 0) {
            int articleId = article.getArticleId();
            List<Tag> list = article.getTagList();
            atrMapper.insertWithList(articleId, list);
            acrMapper.insert(new ArticleCategoryRef(articleId, article.getCategoryId()));
            return 1;
        }
        return 0;
    }

    @Override
    public int update(Article article) {
        if (articleMapper.update(article) >= 0) {
            int articleId = article.getArticleId();
            List<Tag> list = article.getTagList();
            atrMapper.deleteByArticleId(articleId);
            atrMapper.insertWithList(articleId, list);
            acrMapper.update(new ArticleCategoryRef(articleId, article.getCategoryId()));
            return 1;
        }
        return 0;
    }

    @Override
    public int delete(Integer articleId) {
        if (articleMapper.delete(articleId) > 0) {
            atrMapper.deleteByArticleId(articleId);
            acrMapper.delete(articleId);
            return 1;
        }
        return 0;
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
    public int addCommentCount(Integer articleId) {
        return articleMapper.addCommentCount(articleId);
    }

    @Override
    public Article getFullInfoById(int articleId) {
        Article article = articleMapper.getFullInfoById(articleId);
        article.setTagList(atrMapper.getTagListByArticleId(articleId));
        return article;
    }

    @Override
    public List<Article> getTopHotArticle(int num) {
        return articleMapper.getTopHotArticle(num);
    }

    @Override
    public List<Article> getListByCondition(Article article) {
        List<Article> list = articleMapper.getListByCondition(article);
        for (Article a : list) {
            a.setTagList(atrMapper.getTagListByArticleId(a.getArticleId()));
        }
        return list;
    }

    @Override
    public int getTotalCount() {
        return articleMapper.getTotalCount();
    }

    @Override
    public Article getNextArticle(Integer articleId) {
        return articleMapper.getNextArticle(articleId);
    }

    @Override
    public Article getPrevArticle(Integer articleId) {
        return articleMapper.getPrevArticle(articleId);
    }

    @Override
    public int switchShowStatus(Integer articleId) {
        Article article = articleMapper.getSimpleInfoById(articleId);
        if (article == null) {
            return 0;
        }
        if (article.getIsShow() == 0) {
            article.setIsShow(1);
        } else {
            article.setIsShow(0);
        }
        return articleMapper.update(article);
    }
}

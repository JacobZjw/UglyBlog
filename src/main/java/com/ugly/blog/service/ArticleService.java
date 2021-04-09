package com.ugly.blog.service;

import com.ugly.blog.domain.Article;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/5 15:53
 */
public interface ArticleService {
    /**
     * 新增文章
     *
     * @param article 新增的文章
     * @return 成功 > 0
     */
    int insertArticle(Article article);


    /**
     * 更新文章信息
     *
     * @param article 文章
     * @return >0 if success
     */
    int updateArticle(Article article);


    /**
     * 删除文章
     *
     * @param articleId 文章id
     * @return >0 if success
     */
    int deleteArticle(Integer articleId);

    /**
     * 访问量加一
     *
     * @param articleId 文章id
     * @return >0 if success
     */
    int addViewCount(Integer articleId);


    /**
     * 点赞数加一
     *
     * @param articleId 文章id
     * @return >0 if success
     */
    int addLikeCount(Integer articleId);


    /**
     * 根据文章Id获取文章
     *
     * @param articleId 文章id
     * @return 文章详细信息
     */
    Article getArticleById(int articleId);



    /**
     * 获取最热门的几篇文章,默认根据访问数排序
     * @param num 文章数
     * @return article list
     */
    List<Article> getTopHotArticle(int num);


    /**
     * 获取文章总数
     *
     * @return 文章总数
     */
    int getCount();

    /**
     * 获取下一篇文章的id和标题
     *
     * @param articleId 当前文章的id
     * @return article id and title
     */
    Article getNextArticle(Integer articleId);


    /**
     * 获取上一篇文章的id和标题
     *
     * @param articleId 当前文章的id
     * @return article id and title
     */
    Article getPrevArticle(Integer articleId);
}

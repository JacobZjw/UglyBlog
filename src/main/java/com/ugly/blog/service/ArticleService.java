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
     * @param article 文章信息
     * @return 结果
     */
    int insertArticle(Article article);


    /**
     * 更修改文章信息
     *
     * @param article 文章信息
     * @return 结果
     */
    int updateArticle(Article article);


    /**
     * 通过文章ID删除文章
     *
     * @param articleId 文章ID
     * @return 结果
     */
    int deleteArticle(Integer articleId);

    /**
     * 指定ID的文章访问量加一
     *
     * @param articleId 文章ID
     * @return 结果
     */
    int addViewCount(Integer articleId);


    /**
     * 指定ID的文章点赞数加一
     *
     * @param articleId 文章ID
     * @return 结果
     */
    int addLikeCount(Integer articleId);


    /**
     * 根据文章Id获取文章信息
     *
     * @param articleId 文章ID
     * @return 文章对象信息
     */
    Article getArticleById(int articleId);


    /**
     * 获取最热门的几篇文章,默认根据访问数排序
     *
     * @param num 文章数
     * @return 文章对象列表
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
     * @param articleId 当前文章的ID
     * @return 文章对象信息，包含标题和ID
     */
    Article getNextArticle(Integer articleId);


    /**
     * 获取上一篇文章的ID和标题
     *
     * @param articleId 当前文章的ID
     * @return 文章对象信息，包含标题和ID
     */
    Article getPrevArticle(Integer articleId);

    /**
     * 通过文章ID修改文章的状态
     *
     * @param articleId 文章ID
     * @return 结果
     */
    int changeArticleStatus(Integer articleId);
}

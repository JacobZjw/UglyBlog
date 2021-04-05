package com.ugly.blog.service;

import com.ugly.blog.entity.Article;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/5 15:53
 */
public interface ArticleService {

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
}

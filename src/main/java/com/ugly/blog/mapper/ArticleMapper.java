package com.ugly.blog.mapper;

import com.ugly.blog.entity.Article;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/2 16:23
 */
@Repository
public interface ArticleMapper {

    /**
     * 根据文章Id获取文章
     *
     * @param articleId 文章id
     * @return 文章详细信息
     */
    @Select("SELECT * FROM article WHERE article_id=#{articleId}")
    @ResultMap("articleMap")
    Article getById(int articleId);

    /**
     * 获取最热门的几篇文章
     *
     * @param num
     * @return
     */
    @Select("SELECT article_id,article_title FROM article ORDER BY article_view_count DESC LIMIT #{num};")
    @ResultMap("articleMap")
    List<Article> getTopHotArticle(int num);


    /**
     * 获取文章总数
     *
     * @return
     */
    @Select("SELECT COUNT(*) FROM article")
    int getCount();


    /**
     * 获取文章分页
     *
     * @param begin    起始索引
     * @param pageSize 页面大小
     * @return
     */
    @Select("SELECT article_id,article_title,article_view_count,\n" +
            "article_comment_count,article_create_time,\n" +
            "article_summary,article_user_id FROM article LIMIT #{begin},#{pageSize}")
    @ResultMap("articleMap")
    List<Article> getPage(@Param("begin") int begin, @Param("pageSize") int pageSize);


    /**
     * 获取下一篇文章的id和标题
     *
     * @param articleId 当前文章的id
     * @return article id and title OR null
     */
    @Select("SELECT article_id,article_title FROM article WHERE article_id > #{articleId} ORDER BY article_id LIMIT 1;")
    @ResultMap("articleMap")
    Article getNextArticle(Integer articleId);


    /**
     * 获取上一篇文章的id和标题
     *
     * @param articleId 当前文章的id
     * @return article id and title OR null
     */
    @Select("SELECT article_id,article_title FROM article WHERE article_id < #{articleId} ORDER BY article_id DESC LIMIT 1;")
    @ResultMap("articleMap")
    Article getPrevArticle(Integer articleId);
}

package com.ugly.blog.mapper;

import com.ugly.blog.domain.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/2 16:23
 */
@Repository
public interface ArticleMapper {

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
     * 获取文章总数
     *
     * @return 文章总数
     */
    int getCount();

    /**
     * 通过文章ID获取文章
     *
     * @param articleId 文章ID
     * @return 文章对象信息
     */
    Article getById(int articleId);

    /**
     * 根据文章访问数获取最热门的n篇文章
     *
     * @param num 文章数
     * @return 文章对象列表
     */
    List<Article> getTopHotArticle(int num);


    /**
     * 获取文章分页
     *
     * @param begin    起始索引
     * @param pageSize 页面大小
     * @return 文章对象列表
     */
    List<Article> getPage(@Param("begin") int begin, @Param("pageSize") int pageSize);


    /**
     * 获取下一篇文章的ID和标题
     *
     * @param articleId 当前文章的ID
     * @return 下一篇文章对象信息
     */
    Article getNextArticle(Integer articleId);


    /**
     * 获取上一篇文章的ID和标题
     *
     * @param articleId 当前文章的ID
     * @return 上一篇文章的对象信息
     */
    Article getPrevArticle(Integer articleId);


    /**
     * 修改文章信息
     *
     * @param article 文章信息
     * @return 结果
     */
    int updateArticle(Article article);

    /**
     * 新增文章信息
     *
     * @param article 文章信息
     * @return 结果
     */
    int insertArticle(Article article);
}

package com.ugly.blog.mapper;

import com.ugly.blog.domain.Article;
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
    int delete(Integer articleId);

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
     * 指定ID的文章评论数数加一
     *
     * @param articleId 文章ID
     * @return 结果
     */
    int addCommentCount(Integer articleId);


    /**
     * 获取文章总数
     *
     * @return 文章总数
     */
    int getTotalCount();

    /**
     * 获取总浏览量
     * @return 总浏览量
     */
    int getTotalView();


    /**
     * 获取总评论数
     * @return 总评论数
     */
    int getTotalComment();

    /**
     * 通过用户ID查询文章总数
     *
     * @param userId 用户ID
     * @return 结果
     */
    int getCountByUserId(Integer userId);

    /**
     * 通过文章ID获取文章详细信息
     *
     * @param articleId 文章ID
     * @return 文章对象信息
     */
    Article getFullInfoById(int articleId);


    /**
     * 通过文章ID获取文章简要信息
     *
     * @param articleId 文章ID
     * @return 文章对象信息
     */
    Article getSimpleInfoById(int articleId);


    /**
     * 根据文章访问数获取最热门的n篇文章
     *
     * @param num 文章数
     * @return 文章对象列表
     */
    List<Article> getTopHotArticle(int num);


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
     * 根据条件查询文章列表
     *
     * @param article 文章信息
     * @return 文章对象列表
     */
    List<Article> getListByCondition(Article article);


    /**
     * 修改文章信息
     *
     * @param article 文章信息
     * @return 结果
     */
    int update(Article article);

    /**
     * 新增文章信息
     *
     * @param article 文章信息
     * @return 结果
     */
    int insert(Article article);

    /**
     * 根据文章ID获取作者ID
     * @param articleId 文章ID
     * @return
     */
    Integer getAuthorId(Integer articleId);
}

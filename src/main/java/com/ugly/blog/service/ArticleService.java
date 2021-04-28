package com.ugly.blog.service;

import com.ugly.blog.domain.Article;
import org.springframework.security.access.AccessDeniedException;

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
    int insert(Article article);


    /**
     * 更修改文章信息
     *
     * @param article 文章信息
     * @return 结果
     */
    int update(Article article);


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
     * 指定ID的文章评论数加一
     *
     * @param articleId 文章ID
     * @return 结果
     */
    int addCommentCount(Integer articleId);


    /**
     * 根据文章Id获取文章信息
     *
     * @param articleId 文章ID
     * @return 文章对象信息
     */
    Article getFullInfoById(int articleId);


    /**
     * 获取最热门的几篇文章,默认根据访问数排序
     *
     * @param num 文章数
     * @return 文章对象列表
     */
    List<Article> getTopHotArticle(int num);


    /**
     * 根据条件查询文章列表
     *
     * @param article 文章信息
     * @return 文章对象列表
     */
    List<Article> getListByCondition(Article article);

    /**
     * 根据文章ID获取作者ID
     *
     * @param articleId 文章ID
     * @return 作者ID
     */
    Integer getAuthorId(Integer articleId);

    /**
     * 校验是否有权限操作
     * @param articleId 文章ID
     */
    void checkAuthority(Integer articleId) throws AccessDeniedException;

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
     * 切换文章的显示状态
     *
     * @param articleId 文章ID
     * @return 结果
     */
    int switchShowStatus(Integer articleId);
}

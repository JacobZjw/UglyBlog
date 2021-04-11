package com.ugly.blog.mapper;

import com.ugly.blog.domain.Article;
import com.ugly.blog.domain.ArticleTagRef;
import com.ugly.blog.domain.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/6 16:09
 */
public interface ArticleTagRefMapper {


    /**
     * 新增文章和标签关联
     *
     * @param ref 文章和标签关联信息
     * @return 结果
     */
    int insert(ArticleTagRef ref);

    /**
     * 删除文章和标签关联
     *
     * @param ref 文章和标签关联信息
     * @return 结果
     */
    int delete(ArticleTagRef ref);

    /**
     * 查询属于标签列表的所有文章总数
     *
     * @param tagIds 标签ID列表
     * @return 文章总数
     */
    int getCountByTagList(List<Integer> tagIds);


    /**
     * 查询属于标签列表的所有文章
     *
     * @param begin    起始索引
     * @param pageSize 页面大小
     * @param tagIds   标签ID列表
     * @return 文章对象列表
     */
    List<Article> getArticleListByTagList(@Param("begin") int begin, @Param("pageSize") int pageSize, @Param("tagIds") List<Integer> tagIds);


    /**
     * 获取某一标签文章总数
     *
     * @param tagId 标签ID
     * @return 文章总数
     */
    int getCountByTagId(int tagId);


    /**
     * 查询属于某一标签的所有文章
     *
     * @param begin    起始索引
     * @param pageSize 页面大小
     * @param tagId    标签ID
     * @return 文章对象列表
     */
    List<Article> getArticleListByTagId(@Param("begin") int begin, @Param("pageSize") int pageSize, @Param("tagId") int tagId);


    /**
     * 根据文章ID获取所有的标签信息
     *
     * @param articleId 文章ID
     * @return 标签信息列表
     */
    List<Tag> getTagListByArticleId(Integer articleId);

}

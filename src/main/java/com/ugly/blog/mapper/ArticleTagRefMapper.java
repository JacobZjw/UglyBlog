package com.ugly.blog.mapper;

import com.ugly.blog.entity.Article;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/6 16:09
 */
public interface ArticleTagRefMapper {

    /**
     * 获取标签列表的文章总数
     *
     * @param tagIds tagId list
     * @return 文章总数
     */
    int getCountByTagList(List<Integer> tagIds);


    /**
     * 通过标签获取分页
     *
     * @param begin    起始索引
     * @param pageSize 页面大小
     * @param tagIds   tagId list
     * @return article list
     */
    List<Article> getArticleListByTagList(@Param("begin") int begin, @Param("pageSize") int pageSize, @Param("tagIds") List<Integer> tagIds);


    /**
     * 获取某一标签文章总数
     *
     * @param tagId 标签id
     * @return 文章总数
     */
    @Select("SELECT COUNT(*) FROM article_tag_ref at \n" +
            "INNER JOIN article a ON at.article_id = a.article_id \n" +
            "WHERE at.tag_id = #{tagId};")
    int getCountByTagId(int tagId);


    /**
     * 通过标签Id获取分页
     *
     * @param begin    起始索引
     * @param pageSize 页面大小
     * @param tagId    分类id
     * @return article list
     */
    @Select("SELECT a.article_id,a.article_title,a.article_view_count,\n " +
            "a.article_comment_count,a.article_create_time,\n " +
            "a.article_summary,a.article_user_id \n " +
            "FROM article_tag_ref at INNER JOIN article a ON at.article_id = a.article_id \n" +
            "WHERE at.tag_id = #{tagId} LIMIT #{begin},#{pageSize}")
    @ResultMap("articleMap")
    List<Article> getArticleListByTagId(@Param("begin") int begin, @Param("pageSize") int pageSize, @Param("tagId") int tagId);

}

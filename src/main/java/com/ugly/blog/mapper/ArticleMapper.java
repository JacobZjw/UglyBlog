package com.ugly.blog.mapper;

import com.ugly.blog.entity.Article;
import com.ugly.blog.entity.Tag;
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
    @Select("SELECT * FROM article LIMIT #{begin},#{pageSize}")
    @ResultMap("articleMap")
    List<Article> getPage(@Param("begin") int begin, @Param("pageSize") int pageSize);


    /**
     * 获取标签列表的文章总数
     * @param tags
     * @return
     */
    int getCountByTagList(List<Tag> tags);


    /**
     * 通过标签获取分页
     *
     * @param begin    起始索引
     * @param pageSize 页面大小
     * @param tags    标签列表
     * @return
     */
    List<Article> getPageByTagList(int begin, int pageSize, List<Tag> tags);


    /**
     * 获取分类文章总数
     * @param categoryId
     * @return
     */
    @Select("SELECT COUNT(*) FROM article_category_ref ac INNER JOIN article a ON ac.article_id = a.article_id WHERE ac.category_id = #{categoryId}")
    int getCountByCategory(int categoryId);


    /**
     * 通过分类获取分页
     *
     * @param begin      起始索引
     * @param pageSize   页面大小
     * @param categoryId 分类id
     * @return
     */
    @Select("SELECT a.* FROM article_category_ref ac INNER JOIN article a ON ac.article_id = a.article_id WHERE ac.category_id = #{categoryId} LIMIT #{begin},#{pageSize}")
    List<Article> getPageByCategory(int begin, int pageSize, int categoryId);

}

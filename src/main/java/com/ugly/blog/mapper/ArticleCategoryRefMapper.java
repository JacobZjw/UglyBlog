package com.ugly.blog.mapper;

import com.ugly.blog.entity.Article;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/6 16:09
 */
@Repository
public interface ArticleCategoryRefMapper {


    /**
     * 获取某一分类文章总数
     *
     * @param categoryId 分类id
     * @return 文章总数
     */
    @Select("SELECT COUNT(*) FROM article_category_ref ac \n" +
            "INNER JOIN article a ON ac.article_id = a.article_id \n" +
            "WHERE ac.category_id = #{categoryId}")
    int getCountByCategory(int categoryId);


    /**
     * 通过分类获取分页
     *
     * @param begin      起始索引
     * @param pageSize   页面大小
     * @param categoryId 分类id
     * @return article list
     */
    @Select("SELECT a.article_id,a.article_title,a.article_view_count,\n " +
            "a.article_comment_count,a.article_create_time,\n " +
            "a.article_summary,a.article_user_id \n " +
            "FROM article_category_ref ac INNER JOIN article a ON ac.article_id = a.article_id \n" +
            "WHERE ac.category_id = #{categoryId} LIMIT #{begin},#{pageSize}")
    @ResultMap("com.ugly.blog.mapper.ArticleMapper.articleMap")
    List<Article> getPageByCategory(@Param("begin") int begin, @Param("pageSize") int pageSize, @Param("categoryId") int categoryId);

}

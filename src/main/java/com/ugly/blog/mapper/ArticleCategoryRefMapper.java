package com.ugly.blog.mapper;

import com.ugly.blog.domain.Article;
import com.ugly.blog.domain.Category;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/6 16:09
 */
@Repository
public interface ArticleCategoryRefMapper {

    /**
     * 通过文章ID获取分类列表
     *
     * @param articleId 文章ID
     * @return 文章对象列表
     */
    List<Category> getCategoryListByArticleId(Integer articleId);


    /**
     * 通过文章ID获取分类
     *
     * @param articleId 文章ID
     * @return 文章对象
     */
    Category getCategoryByArticleId(Integer articleId);

    /**
     * 某一分类文章总数
     *
     * @param categoryId 分类ID
     * @return 文章总数
     */
    int getCountByCategory(int categoryId);


    /**
     * 通过分类ID获取分页
     *
     * @param begin      起始索引
     * @param pageSize   页面大小
     * @param categoryId 分类ID
     * @return 文章对象列表
     */
    List<Article> getPageByCategory(@Param("begin") int begin, @Param("pageSize") int pageSize, @Param("categoryId") int categoryId);

}

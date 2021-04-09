package com.ugly.blog.mapper;

import com.ugly.blog.domain.Article;
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
     * 某一分类文章总数
     *
     * @param categoryId 分类id
     * @return 文章总数
     */
    int getCountByCategory(int categoryId);


    /**
     * 通过分类获取分页
     *
     * @param begin      起始索引
     * @param pageSize   页面大小
     * @param categoryId 分类id
     * @return article list
     */
    List<Article> getPageByCategory(@Param("begin") int begin, @Param("pageSize") int pageSize, @Param("categoryId") int categoryId);

}

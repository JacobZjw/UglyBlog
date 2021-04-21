package com.ugly.blog.mapper;

import com.ugly.blog.domain.Category;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/2 19:19
 */
@Repository
public interface CategoryMapper {

    /**
     * 新增分类信息
     *
     * @param categoryName 分类名
     * @return 结果
     */
    int insert(@Param("categoryName") String categoryName);

    /**
     * 通过文章ID删除分类信息
     *
     * @param categoryId 分类ID
     * @return 结果
     */
    int deleteById(@Param("categoryId") Integer categoryId);


    /**
     * 修改分类信息
     *
     * @param category 分类信息
     * @return 结果
     */
    int update(Category category);


    /**
     * 通过分类ID查找分类信息
     *
     * @param categoryId 分类ID
     * @return 分类对象信息
     */
    Category getById(@Param("categoryId") Integer categoryId);


    /**
     * 通过分类名查找分类信息
     *
     * @param categoryName 分类名
     * @return 分类对象信息
     */
    Category getByName(@Param("categoryName") String categoryName);


    /**
     * 查找所有的分类
     *
     * @return 分类对象列表
     */
    List<Category> getList(String categoryName);

    /**
     * 根据分类所属的文章数查找前n个分类对象信息
     *
     * @param num 分类数
     * @return 分类对象列表
     */
    List<Category> getTopNumsCategoryList(int num);


}

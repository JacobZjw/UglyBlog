package com.ugly.blog.mapper;

import com.ugly.blog.domain.Category;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/2 19:19
 */
public interface CategoryMapper {

    /**
     * 新增分类信息
     *
     * @param categoryName 分类名
     * @return 结果
     */
    int insertCategory(String categoryName);

    /**
     * 通过文章ID删除分类信息
     *
     * @param categoryId 分类ID
     * @return 结果
     */
    int deleteCategoryById(Integer categoryId);


    /**
     * 修改分类信息
     *
     * @param category 分类信息
     * @return 结果
     */
    int updateCategory(Category category);


    /**
     * 通过分类ID查找分类信息
     *
     * @param categoryId 分类ID
     * @return 分类对象信息
     */
    Category getCategoryById(Integer categoryId);


    /**
     * 查找所有的分类
     *
     * @return 分类对象列表
     */
    List<Category> getAllCategoryList();

    /**
     * 根据分类所属的文章数查找前n个分类对象信息
     *
     * @param num 分类数
     * @return 分类对象列表
     */
    List<Category> getTopNumsCategoryList(int num);


}

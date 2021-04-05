package com.ugly.blog.service;

import com.ugly.blog.entity.Category;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/5 14:42
 */
public interface CategoryService {
    List<Category> getTopNumsCategoryList(int num);

    List<Category> getAllCategoryList();

    Category getCategoryById(Integer categoryId);

    int updateCategory(Integer categoryId, String categoryName);

    int insertCategory(String categoryName);

    int deleteCategoryById(Integer categoryId);
}
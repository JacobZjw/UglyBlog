package com.ugly.blog.service;

import com.ugly.blog.domain.Category;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/5 14:42
 */
public interface CategoryService {
    List<Category> getTopNumsCategoryList(int num);

    List<Category> getAllCategoryList();

    Category getCategoryById(Integer categoryId);

    Category getByCategoryName(String categoryName);

    int updateOrInsertCategory(Category category);

    int insertCategory(String categoryName);

    int deleteCategoryById(Integer categoryId);
}

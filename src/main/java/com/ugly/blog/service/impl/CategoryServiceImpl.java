package com.ugly.blog.service.impl;

import com.ugly.blog.entity.Category;
import com.ugly.blog.mapper.CategoryMapper;
import com.ugly.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/5 15:03
 */
@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public List<Category> getTopNumsCategoryList(int num) {
        return categoryMapper.getTopNumsCategoryList(num);
    }

    @Override
    public List<Category> getAllCategoryList() {
        return categoryMapper.getAllCategoryList();
    }

    @Override
    public Category getCategoryById(Integer categoryId) {
        return categoryMapper.getCategoryById(categoryId);
    }

    @Override
    public int updateCategory(Integer categoryId, String categoryName) {
        return categoryMapper.updateCategory(new Category(categoryId, categoryName));
    }

    @Override
    public int insertCategory(String categoryName) {
        return categoryMapper.insertCategory(categoryName);
    }

    @Override
    public int deleteCategoryById(Integer categoryId) {
        return categoryMapper.deleteCategoryById(categoryId);
    }
}

package com.ugly.blog.service.impl;

import com.ugly.blog.domain.Category;
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
    public List<Category> getCategoryList(String name) {
        return categoryMapper.getList(name);
    }

    @Override
    public Category getCategoryById(Integer categoryId) {
        return categoryMapper.getById(categoryId);
    }

    @Override
    public Category getByCategoryName(String categoryName) {
        return categoryMapper.getByName(categoryName);
    }

    @Override
    public int updateOrInsertCategory(Category category) {
        if (category.getCategoryId() == null) {
            return insertCategory(category.getCategoryName());
        }
        return categoryMapper.update(category);
    }

    @Override
    public int insertCategory(String categoryName) {
        if (getByCategoryName(categoryName) != null) {
            return 0;
        }
        return categoryMapper.insert(categoryName);
    }

    @Override
    public int deleteCategoryById(Integer categoryId) {
        return categoryMapper.deleteById(categoryId);
    }
}

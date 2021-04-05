package com.ugly.blog.mapper;

import com.ugly.blog.BaseTest;
import com.ugly.blog.entity.Category;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/5 15:15
 */
public class CategoryMapperTest extends BaseTest {
    @Autowired
    private CategoryMapper categoryMapper;


    @Test
    public void getCategoryById() {
        Category category = categoryMapper.getCategoryById(3);
        Assert.assertEquals("Java", category.getCategoryName());
    }

    @Test
    public void getAllCategoryList() {
        categoryMapper.getAllCategoryList().forEach(System.out::println);
    }

    @Test
    public void getTopNumsCategoryList() {
        int nums = 4;
        List<Category> list = categoryMapper.getTopNumsCategoryList(nums);
        Assert.assertEquals(list.size(), nums);
    }

    @Test
    public void updateCategory() {
    }

    @Test
    public void insertCategory() {
    }

    @Test
    public void deleteCategoryById() {
    }
}
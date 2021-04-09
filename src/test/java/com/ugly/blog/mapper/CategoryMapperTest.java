package com.ugly.blog.mapper;

import com.ugly.blog.BaseTest;
import com.ugly.blog.domain.Category;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author JwZheng
 * @date 2021/4/9 16:53
 */
public class CategoryMapperTest extends BaseTest {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    @Ignore
    public void insertCategory() {
    }

    @Test
    @Ignore
    public void deleteCategoryById() {
    }

    @Test
    @Ignore
    public void updateCategory() {

    }

    @Test
    public void getCategoryById() {
        assertNotNull(categoryMapper.getCategoryById(1));
    }

    @Test
    public void getAllCategoryList() {
        assertNotNull(categoryMapper.getAllCategoryList());
    }

    @Test
    public void getTopNumsCategoryList() {
        List<Category> list = categoryMapper.getTopNumsCategoryList(4);
        assertEquals(4, list.size());
    }
}
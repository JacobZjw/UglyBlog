package com.ugly.blog.mapper;

import com.ugly.blog.BaseTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

/**
 * @author JwZheng
 * @date 2021/4/6 1:24
 */
public class TagMapperTest extends BaseTest {
    @Autowired
    private TagMapper tagMapper;

    @Test
    @Ignore
    public void insertTag() {
    }

    @Test
    @Ignore
    public void deleteTagById() {
    }

    @Test
    @Ignore
    public void updateTag() {
    }

    @Test
    public void getTagById() {
        assertNotNull(tagMapper.getTagById(1));
    }

    @Test
    public void getAllTagList() {
        assertNotNull(tagMapper.getAllTagList());
    }
}
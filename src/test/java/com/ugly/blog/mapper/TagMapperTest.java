package com.ugly.blog.mapper;

import com.ugly.blog.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author JwZheng
 * @date 2021/4/6 1:24
 */
public class TagMapperTest extends BaseTest {
    @Autowired
    private TagMapper tagMapper;

    @Test
    public void getAllTagList() {
        tagMapper.getAllTagList().forEach(System.out::println);
    }
}
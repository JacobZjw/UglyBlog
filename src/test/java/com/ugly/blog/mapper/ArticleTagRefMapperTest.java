package com.ugly.blog.mapper;

import com.ugly.blog.BaseTest;
import com.ugly.blog.domain.Tag;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/13 12:52
 */
public class ArticleTagRefMapperTest extends BaseTest {

    @Autowired
    private ArticleTagRefMapper articleTagRefMapper;

    @Test
    public void insertWithList() {
        List<Tag> list = new ArrayList<>();
        for (int i = 10; i < 15; i++) {
            Tag tag = new Tag();
            tag.setTagId(i);
            list.add(tag);
        }
        articleTagRefMapper.insertWithList(20, list);
    }
}
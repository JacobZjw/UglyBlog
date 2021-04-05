package com.ugly.blog.service.impl;

import com.ugly.blog.entity.Tag;
import com.ugly.blog.mapper.TagMapper;
import com.ugly.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/6 1:20
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> getTagList() {
        return tagMapper.getAllTagList();
    }
}

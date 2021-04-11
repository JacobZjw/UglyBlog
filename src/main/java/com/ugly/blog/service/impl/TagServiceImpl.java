package com.ugly.blog.service.impl;

import com.ugly.blog.domain.Tag;
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

    private final TagMapper tagMapper;

    @Autowired
    public TagServiceImpl(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    @Override
    public List<Tag> getTagList() {
        return tagMapper.getAllTagList();
    }

    @Override
    public Tag getTagById(Integer tagId) {
        return tagMapper.getTagById(tagId);
    }

    @Override
    public int insertTag(String tagName) {
        if (getByTagName(tagName) != null) {
            return 0;
        }
        return tagMapper.insertTag(tagName);
    }

    @Override
    public int deleteTagById(Integer tagId) {
        return tagMapper.deleteTagById(tagId);
    }

    @Override
    public int updateOrInsertTag(Tag tag) {
        if (tag.getTagId() != null) {
            return tagMapper.updateTag(tag);
        }
        return tagMapper.insertTag(tag.getTagName());
    }

    @Override
    public Tag getByTagName(String tagName) {
        return tagMapper.getByTagName(tagName);
    }
}

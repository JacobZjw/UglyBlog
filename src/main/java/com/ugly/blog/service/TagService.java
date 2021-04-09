package com.ugly.blog.service;

import com.ugly.blog.domain.Tag;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/5 14:42
 */
public interface TagService {
    /**
     * 获取标签列表
     *
     * @return
     */
    List<Tag> getTagList();

    Tag getTagById(Integer tagId);
}

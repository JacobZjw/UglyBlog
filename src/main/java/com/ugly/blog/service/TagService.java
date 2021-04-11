package com.ugly.blog.service;

import com.ugly.blog.domain.Tag;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/5 14:42
 */
public interface TagService {

    List<Tag> getTagList();

    Tag getTagById(Integer tagId);


    /**
     * 新增标签信息
     *
     * @param tagName 标签名
     * @return 结果
     */
    int insertTag(String tagName);


    /**
     * 通过标签ID删除标签信息
     *
     * @param tagId 标签ID
     * @return 结果
     */
    int deleteTagById(Integer tagId);

    /**
     * 修改标签信息
     *
     * @param tag 标签信息
     * @return 结果
     */
    int updateOrInsertTag(Tag tag);


    /**
     * 根据标签名寻找标签信息
     *
     * @param tagName 标签名
     * @return 标签对象信息
     */
    Tag getByTagName(String tagName);

}

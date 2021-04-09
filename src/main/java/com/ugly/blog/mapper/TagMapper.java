package com.ugly.blog.mapper;

import com.ugly.blog.domain.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/2 19:19
 */
@Repository
public interface TagMapper {


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
    int updateTag(Tag tag);

    /**
     * 根据标签ID寻找标签信息
     *
     * @param tagId 标签ID
     * @return 标签对象信息
     */
    Tag getTagById(Integer tagId);


    /**
     * 查询所有的标签信息
     *
     * @return 标签对象列表
     */
    List<Tag> getAllTagList();
}

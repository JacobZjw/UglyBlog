package com.ugly.blog.mapper;

import com.ugly.blog.entity.Tag;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/2 19:19
 */
@Repository
public interface TagMapper {

    /**
     * 根据id寻找tag
     *
     * @param tagId
     * @return tag或null
     */
    @Select("SELECT * FROM tag WHERE tag_id=#{tagId}")
    @Results(id = "tagMap", value = {
            @Result(property = "tagId", column = "tag_id", id = true),
            @Result(property = "tagName", column = "tag_name")
    })
    Tag getTagById(Integer tagId);


    /**
     * 获取所有的标签
     *
     * @return 标签List
     */
    @Select("SELECT * FROM tag")
    @ResultMap("tagMap")
    List<Tag> getAllTagList();

    /**
     * 更新标签
     *
     * @param tag
     * @return
     */
    @Update("UPDATE tag SET tag_name=#{tagName} WHERE tag_id=#{tagId}")
    @ResultMap("tagMap")
    int updateTag(Tag tag);

    /**
     * 新增标签
     *
     * @param tagName
     * @return
     */
    @Insert("INSERT tag(tag_name)VALUES(#{tagName})")
    @ResultMap("tagMap")
    int insertTag(String tagName);

    /**
     * 根据id删除标签
     *
     * @param tagId
     * @return
     */
    @Delete("DELETE tag WHERE tag_id=#{tagId}")
    @ResultMap("tagMap")
    int deleteTagById(Integer tagId);
}

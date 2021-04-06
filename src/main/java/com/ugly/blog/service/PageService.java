package com.ugly.blog.service;

import com.ugly.blog.dto.Page;
import com.ugly.blog.entity.Article;
import com.ugly.blog.entity.Category;
import com.ugly.blog.entity.Tag;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/2 20:25
 */
public interface PageService {

    /**
     * 获取默认的分页
     *
     * @param pageNo   页号
     * @param pageSize 每页显示的条数
     * @return
     */
    Page<Article> getDefaultPage(int pageNo, int pageSize);

    /**
     * 根据标签获取分页
     * @param pageNo 页号
     * @param pageSize 每页显示的条数
     * @param tags 标签列表
     * @return
     */
    Page<Article> getPageByTags(int pageNo, int pageSize, List<Tag> tags);

    /**
     * 根据一个标签获取分页
     * @param pageNo 页号
     * @param pageSize 每页显示的条数
     * @param tagId 标签id
     * @return article page
     */
    Page<Article> getPageByTagId(int pageNo, int pageSize, int tagId);


    /**
     * 根据分类获取分页
     * @param pageNo 页号
     * @param pageSize 每页显示的条数
     * @param categoryId 分类id
     * @return
     */
    Page<Article> getPageByCategory(int pageNo, int pageSize, int categoryId);
}

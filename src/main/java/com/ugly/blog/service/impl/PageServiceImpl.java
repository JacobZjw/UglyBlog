package com.ugly.blog.service.impl;

import com.ugly.blog.domain.Article;
import com.ugly.blog.dto.Page;
import com.ugly.blog.mapper.ArticleCategoryRefMapper;
import com.ugly.blog.mapper.ArticleMapper;
import com.ugly.blog.mapper.ArticleTagRefMapper;
import com.ugly.blog.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/2 20:27
 */
@Service
public class PageServiceImpl implements PageService {

    private final ArticleMapper articleMapper;

    private final ArticleTagRefMapper articleTagRefMapper;
    private final ArticleCategoryRefMapper articleCategoryRefMapper;

    @Autowired
    public PageServiceImpl(ArticleMapper articleMapper, ArticleTagRefMapper articleTagRefMapper, ArticleCategoryRefMapper articleCategoryRefMapper) {
        this.articleMapper = articleMapper;
        this.articleTagRefMapper = articleTagRefMapper;
        this.articleCategoryRefMapper = articleCategoryRefMapper;
    }

    @Override
    public Page<Article> getDefaultPage(int pageNo, int pageSize) {
        int totalCount = articleMapper.getCount();
        Page<Article> page = addDataToPage(pageNo, pageSize, totalCount);
        int begin = (page.getPageNo() - 1) * pageSize;
        page.setItems(articleMapper.getPage(begin, pageSize));
        return page;
    }

    @Override
    public Page<Article> getListByCondition(int pageNo, int pageSize, Article article) {
        int totalCount = articleMapper.getCount();
        Page<Article> page = addDataToPage(pageNo, pageSize, totalCount);
        int begin = (page.getPageNo() - 1) * pageSize;
        page.setItems(articleMapper.getListByCondition(begin, pageSize, article));
        return page;
    }


    @Override
    public Page<Article> getPageByTags(int pageNo, int pageSize, List<Integer> tags) {
        int totalCount = articleTagRefMapper.getCountByTagList(tags);
        Page<Article> page = addDataToPage(pageNo, pageSize, totalCount);
        int begin = (page.getPageNo() - 1) * pageSize;
        page.setItems(articleTagRefMapper.getArticleListByTagList(begin, pageSize, tags));
        return page;
    }

    @Override
    public Page<Article> getPageByTagId(int pageNo, int pageSize, int tagId) {
        int totalCount = articleTagRefMapper.getCountByTagId(tagId);
        Page<Article> page = addDataToPage(pageNo, pageSize, totalCount);
        int begin = (page.getPageNo() - 1) * pageSize;
        page.setItems(articleTagRefMapper.getArticleListByTagId(begin, pageSize, tagId));
        return page;
    }

    @Override
    public Page<Article> getPageByCategory(int pageNo, int pageSize, int categoryId) {
        int totalCount = articleCategoryRefMapper.getCountByCategory(categoryId);
        Page<Article> page = addDataToPage(pageNo, pageSize, totalCount);
        int begin = (page.getPageNo() - 1) * pageSize;
        page.setItems(articleCategoryRefMapper.getPageByCategory(begin, pageSize, categoryId));
        return page;
    }

    private Page<Article> addDataToPage(int pageNo, int pageSize, int totalCount) {
        Page<Article> page = new Page<>();
        int totalPage = totalCount / pageSize;
        if (totalCount % pageSize != 0 || totalPage == 0) {
            ++totalPage;
        }
        pageNo = Math.max(1, pageNo);
        pageNo = Math.min(pageNo, totalPage);

        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotalPage(totalPage);
        page.setTotalCount(totalCount);
        return page;
    }

}

package com.ugly.blog.controller.common;

import com.ugly.blog.constant.PageConstant;
import com.ugly.blog.domain.Article;
import com.ugly.blog.domain.Tag;
import com.ugly.blog.dto.AjaxResult;
import com.ugly.blog.dto.Page;
import com.ugly.blog.service.PageService;
import com.ugly.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/6 1:39
 */
@Controller
public class TagController {

    private final PageService pageService;

    private final TagService tagService;

    @Autowired
    public TagController(PageService pageService, TagService tagService) {
        this.pageService = pageService;
        this.tagService = tagService;
    }

    @RequestMapping(value = "tag/{tagId}", method = RequestMethod.GET)
    public String getArticle(@PathVariable("tagId") Integer tagId,
                             @RequestParam(required = false, defaultValue = PageConstant.DEFAULT_PAGE_INDEX) Integer pageIndex,
                             @RequestParam(required = false, defaultValue = PageConstant.DEFAULT_PAGE_SIZE) Integer pageSize, Model model) {

        Page<Article> page = pageService.getPageByTagId(pageIndex, pageSize, tagId);
        model.addAttribute("articlePage", page);

        List<Tag> tagList = tagService.getTagList(null);
        model.addAttribute("tagList", tagList);

        return "articlesByTag";
    }

    @RequestMapping(value = "tag/all", method = RequestMethod.GET)
    public String getArticle(@RequestParam(required = false, defaultValue = PageConstant.DEFAULT_PAGE_INDEX) Integer pageIndex,
                             @RequestParam(required = false, defaultValue = PageConstant.DEFAULT_PAGE_SIZE) Integer pageSize, Model model) {
        Page<Article> page = pageService.getArticleDefaultPage(pageIndex, pageSize);
        model.addAttribute("articlePage", page);

        List<Tag> tagList = tagService.getTagList(null);
        model.addAttribute("tagList", tagList);

        return "articlesByTag";
    }


    @RequestMapping(value = "tag/tagName/{tagId}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getTagName(@PathVariable("tagId") Integer tagId) {
        Tag tag = tagService.getTagById(tagId);
        return tag == null ? AjaxResult.error("Tag Not Found") : AjaxResult.success("Success!", tag.getTagName());
    }

}

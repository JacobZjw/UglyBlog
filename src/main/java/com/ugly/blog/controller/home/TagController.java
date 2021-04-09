package com.ugly.blog.controller.home;

import com.ugly.blog.config.AppConfig;
import com.ugly.blog.domain.Article;
import com.ugly.blog.domain.Tag;
import com.ugly.blog.dto.JSONResult;
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
                             @RequestParam(required = false, defaultValue = AppConfig.DEFAULT_PAGE_INDEX) Integer pageIndex,
                             @RequestParam(required = false, defaultValue = AppConfig.DEFAULT_PAGE_SIZE) Integer pageSize, Model model) {

        Page<Article> page = pageService.getPageByTagId(pageIndex, pageSize, tagId);
        model.addAttribute("articlePage", page);

        List<Tag> tagList = tagService.getTagList();
        model.addAttribute("tagList", tagList);

        return "home/articlesByTag";
    }

    @RequestMapping(value = "tag/all", method = RequestMethod.GET)
    public String getArticle(@RequestParam(required = false, defaultValue = AppConfig.DEFAULT_PAGE_INDEX) Integer pageIndex,
                             @RequestParam(required = false, defaultValue = AppConfig.DEFAULT_PAGE_SIZE) Integer pageSize, Model model) {
        Page<Article> page = pageService.getDefaultPage(pageIndex, pageSize);
        model.addAttribute("articlePage", page);

        List<Tag> tagList = tagService.getTagList();
        model.addAttribute("tagList", tagList);

        return "home/articlesByTag";
    }

    @RequestMapping(value = "tag/tagName/{tagId}", method = RequestMethod.GET)
    @ResponseBody
    public JSONResult getTagName(@PathVariable("tagId") Integer tagId) {
        JSONResult result = new JSONResult();
        Tag tag = tagService.getTagById(tagId);
        if (tag == null) {
            result.setCode(404);
            result.setMsg("Tag Not Found");
        } else {
            result.setCode(200);
            result.setMsg("success");
            result.setData(tag.getTagName());
        }
        return result;
    }

}

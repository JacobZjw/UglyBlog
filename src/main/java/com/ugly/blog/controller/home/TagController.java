package com.ugly.blog.controller.home;

import com.ugly.blog.config.AppConfig;
import com.ugly.blog.dto.Page;
import com.ugly.blog.entity.Article;
import com.ugly.blog.entity.Tag;
import com.ugly.blog.service.PageService;
import com.ugly.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/6 1:39
 */
@Controller
public class TagController {
    @Autowired
    private PageService pageService;

    @Autowired
    private TagService tagService;


    @RequestMapping(value = "tag/{tagId}", method = RequestMethod.GET)
    public String getArticle(@PathVariable("tagId") Integer tagId, Model model) {

        Page<Article> page = pageService.getPageByTagId(AppConfig.DEFAULT_PAGE_INDEX, AppConfig.DEFAULT_PAGE_SIZE, tagId);
        model.addAttribute("articlePage", page);

        List<Tag> tagList = tagService.getTagList();
        model.addAttribute("tagList", tagList);

        return "Home/articlesByTag";
    }

}

package com.ugly.blog.controller.home;


import cn.hutool.json.JSONObject;
import com.ugly.blog.dto.Page;
import com.ugly.blog.entity.Article;
import com.ugly.blog.entity.Category;
import com.ugly.blog.entity.Tag;
import com.ugly.blog.service.ArticleService;
import com.ugly.blog.service.CategoryService;
import com.ugly.blog.service.PageService;
import com.ugly.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/2 20:49
 */
@Controller
public class IndexController {

    @Autowired
    private PageService pageService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;


    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                        @RequestParam(required = false, defaultValue = "10") Integer pageSize, Model model) {
        Page<Article> page = pageService.getDefaultPage(pageIndex, pageSize);
        model.addAttribute("articlePage", page);

        List<Category> categoryList = categoryService.getTopNumsCategoryList(4);
        model.addAttribute("categoryList", categoryList);

        List<Article> hotArticles = articleService.getTopHotArticle(5);
        model.addAttribute("hotArticles",hotArticles);


        List<Tag> tagList = tagService.getTagList();
        model.addAttribute("tagList",tagList);
        return "Home/home";
    }

    @RequestMapping(value = "/page/{pageIndex}", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getPage(@PathVariable("pageIndex") Integer pageIndex) {
        Page<Article> page = pageService.getDefaultPage(pageIndex, 10);
        return new JSONObject(page).toString();
    }

}
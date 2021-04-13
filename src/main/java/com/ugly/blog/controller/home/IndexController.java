package com.ugly.blog.controller.home;


import cn.hutool.json.JSONObject;
import com.ugly.blog.config.AppConfig;
import com.ugly.blog.constant.PageConstant;
import com.ugly.blog.domain.Article;
import com.ugly.blog.domain.Category;
import com.ugly.blog.domain.Tag;
import com.ugly.blog.dto.Page;
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

    private final PageService pageService;

    private final ArticleService articleService;

    private final CategoryService categoryService;

    private final TagService tagService;

    @Autowired
    public IndexController(PageService pageService, ArticleService articleService, CategoryService categoryService, TagService tagService) {
        this.pageService = pageService;
        this.articleService = articleService;
        this.categoryService = categoryService;
        this.tagService = tagService;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(@RequestParam(required = false, defaultValue = PageConstant.DEFAULT_PAGE_INDEX) Integer pageIndex,
                        @RequestParam(required = false, defaultValue = PageConstant.DEFAULT_PAGE_SIZE) Integer pageSize, Model model) {
        Page<Article> page = pageService.getArticleDefaultPage(pageIndex, pageSize);
        model.addAttribute("articlePage", page);

        List<Category> categoryList = categoryService.getTopNumsCategoryList(AppConfig.SIDEBAR_CATEGORY_NUM);
        model.addAttribute("categoryList", categoryList);

        List<Article> hotArticles = articleService.getTopHotArticle(AppConfig.SIDEBAR_HOT_ARTICLE_NUM);
        model.addAttribute("hotArticles", hotArticles);


        List<Tag> tagList = tagService.getTagList(null);
        model.addAttribute("tagList", tagList);
        return "home/home";
    }

    @RequestMapping(value = "/page/{pageIndex}", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getPage(@PathVariable("pageIndex") Integer pageIndex) {
        Page<Article> page = pageService.getArticleDefaultPage(pageIndex, 10);
        return new JSONObject(page).toString();
    }

}

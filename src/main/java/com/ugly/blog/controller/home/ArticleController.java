package com.ugly.blog.controller.home;

import com.ugly.blog.entity.Article;
import com.ugly.blog.entity.Category;
import com.ugly.blog.entity.Tag;
import com.ugly.blog.service.ArticleService;
import com.ugly.blog.service.CategoryService;
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
 * @date 2021/4/5 17:15
 */
@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/article/{articleId}", method = RequestMethod.GET)
    public String getArticle(@PathVariable("articleId") Integer articleId, Model model) {
        Article article = articleService.getArticleById(articleId);
        model.addAttribute("article", article);

        List<Category> categoryList = categoryService.getTopNumsCategoryList(4);
        model.addAttribute("categoryList", categoryList);

        List<Tag> tagList = tagService.getTagList();
        model.addAttribute("tagList",tagList);
        return "Home/article";
    }
}

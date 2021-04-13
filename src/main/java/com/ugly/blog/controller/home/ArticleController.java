package com.ugly.blog.controller.home;

import com.ugly.blog.config.AppConfig;
import com.ugly.blog.domain.Article;
import com.ugly.blog.domain.Category;
import com.ugly.blog.domain.Tag;
import com.ugly.blog.dto.PrevAndNextArticle;
import com.ugly.blog.service.ArticleService;
import com.ugly.blog.service.CategoryService;
import com.ugly.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/5 17:15
 */
@Controller
public class ArticleController {

    private final ArticleService articleService;

    private final CategoryService categoryService;

    private final TagService tagService;

    @Autowired
    public ArticleController(ArticleService articleService, CategoryService categoryService, TagService tagService) {
        this.articleService = articleService;
        this.categoryService = categoryService;
        this.tagService = tagService;
    }

    @RequestMapping(value = "/article/{articleId}", method = RequestMethod.GET)
    public String getArticle(@PathVariable("articleId") Integer articleId, Model model) {
        Article article = articleService.getFullInfoById(articleId);
        //TODO:may by can use aop
        articleService.addViewCount(articleId);
        model.addAttribute("article", article);

        List<Category> categoryList = categoryService.getTopNumsCategoryList(AppConfig.SIDEBAR_CATEGORY_NUM);
        model.addAttribute("categoryList", categoryList);

        List<Article> hotArticles = articleService.getTopHotArticle(AppConfig.SIDEBAR_HOT_ARTICLE_NUM);
        model.addAttribute("hotArticles", hotArticles);


        List<Tag> tagList = tagService.getTagList(null);
        model.addAttribute("tagList", tagList);
        return "home/article";
    }

    /**
     * 上一篇与下一篇模块，前台通过AJAX交互
     *
     * @param articleId 当前文章id
     * @return 上一篇与下一篇文章的id和标题
     */
    @RequestMapping(value = "/article/{articleId}/prevAndNext", method = RequestMethod.GET)
    @ResponseBody
    public PrevAndNextArticle getPrevAndNextArticle(@PathVariable("articleId") Integer articleId) {
        Article nextArticle = articleService.getNextArticle(articleId);
        Article prevArticle = articleService.getPrevArticle(articleId);

        PrevAndNextArticle prevAndNextArticle = new PrevAndNextArticle();
        if (nextArticle != null) {
            prevAndNextArticle.setNextArticleId(nextArticle.getArticleId());
            prevAndNextArticle.setNextArticleTitle(nextArticle.getTitle());
        }
        if (prevArticle != null) {
            prevAndNextArticle.setPrevArticleId(prevArticle.getArticleId());
            prevAndNextArticle.setPrevArticleTitle(prevArticle.getTitle());
        }
        return prevAndNextArticle;
    }


}

package com.ugly.blog.controller.system;

import com.ugly.blog.constant.PageConstant;
import com.ugly.blog.controller.common.BaseController;
import com.ugly.blog.domain.Article;
import com.ugly.blog.domain.User;
import com.ugly.blog.dto.AjaxResult;
import com.ugly.blog.dto.Page;
import com.ugly.blog.dto.TableDataInfo;
import com.ugly.blog.service.ArticleService;
import com.ugly.blog.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author JwZheng
 * @date 2021/4/9 22:56
 */
@Controller
@RequestMapping("api/system/article")
public class SysArticleController extends BaseController {

    @Autowired
    private PageService pageService;

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public TableDataInfo getTableData(@RequestParam(required = false, defaultValue = PageConstant.DEFAULT_PAGE_INDEX) Integer pageIndex,
                                      @RequestParam(required = false, defaultValue = PageConstant.DEFAULT_PAGE_SIZE) Integer pageSize) {
        Page<Article> page = pageService.getPageByCondition(pageIndex, pageSize, null);
        return getDataTable(page);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public TableDataInfo getTableDataByCondition(@RequestParam(required = false, defaultValue = PageConstant.DEFAULT_PAGE_INDEX) Integer pageIndex,
                                                 @RequestParam(required = false, defaultValue = PageConstant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                                 String nickname, String title) {
        Article article = new Article();
        article.setTitle(title);
        User user = new User();
        user.setNickname(nickname);
        article.setUser(user);
        Page<Article> page = pageService.getPageByCondition(pageIndex, pageSize, article);
        return getDataTable(page);
    }


    @RequestMapping(value = "/{articleId}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getArticleDetails(@PathVariable("articleId") Integer articleId) {
        return toAjax(articleService.getArticleById(articleId));
    }

    @RequestMapping(value = "/delete/{articleId}", method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResult deleteArticle(@PathVariable("articleId") Integer articleId) {
        return toAjax(articleService.deleteArticle(articleId));
    }

    @RequestMapping(value = "/status/{articleId}/change", method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResult changeArticleStatus(@PathVariable("articleId") Integer articleId) {
        return toAjax(articleService.changeArticleStatus(articleId));
    }

    @RequestMapping(value = "/insert/", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult insertArticle(Article article) {
        return toAjax(articleService.insertArticle(article));
    }

    @RequestMapping(value = "/update/", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult updateArticle(Article article) {
        return toAjax(articleService.updateArticle(article));
    }

}

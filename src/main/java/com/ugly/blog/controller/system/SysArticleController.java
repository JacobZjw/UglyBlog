package com.ugly.blog.controller.system;

import com.ugly.blog.constant.PageConstant;
import com.ugly.blog.controller.BaseController;
import com.ugly.blog.domain.Article;
import com.ugly.blog.domain.User;
import com.ugly.blog.dto.AjaxResult;
import com.ugly.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/9 22:56
 */
@Controller
@RequestMapping("api/system/article")
public class SysArticleController extends BaseController {

    private final ArticleService articleService;

    @Autowired
    public SysArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    @RolesAllowed("user")
    public AjaxResult getTableDataByCondition(@RequestParam(required = false, defaultValue = PageConstant.DEFAULT_PAGE_INDEX) Integer pageIndex,
                                              @RequestParam(required = false, defaultValue = PageConstant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                              Article article,
                                              User user) {
        if (article != null) {
            article.setUser(user);
        }
        startPage(pageIndex, pageSize);
        List<Article> list = articleService.getListByCondition(article);
        return getDataTable(list);
    }


    @RequestMapping(value = "/{articleId}", method = RequestMethod.GET)
    @ResponseBody
    @RolesAllowed("user")
    public AjaxResult getDetails(@PathVariable("articleId") Integer articleId) {
        return toAjax(articleService.getFullInfoById(articleId));
    }

    @RequestMapping(value = "/delete/{articleId}", method = RequestMethod.PUT)
    @ResponseBody
    @RolesAllowed("user")
    public AjaxResult delete(@PathVariable("articleId") Integer articleId) {
        return toAjax(articleService.delete(articleId));
    }

    @RequestMapping(value = "/show/{articleId}/switch", method = RequestMethod.PUT)
    @ResponseBody
    @RolesAllowed("user")
    public AjaxResult switchShowStatus(@PathVariable("articleId") Integer articleId) {
        return toAjax(articleService.switchShowStatus(articleId));
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    @RolesAllowed("user")
    public AjaxResult insert(@RequestBody Article article) {
        return toAjax(articleService.insert(article));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    @RolesAllowed("user")
    public AjaxResult update(@RequestBody Article article) {
        System.out.println(article);
        return toAjax(articleService.update(article));
    }

}

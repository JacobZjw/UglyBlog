package com.ugly.blog.controller.admin;

import com.ugly.blog.constant.PageConstant;
import com.ugly.blog.controller.common.BaseController;
import com.ugly.blog.domain.Article;
import com.ugly.blog.domain.User;
import com.ugly.blog.dto.Page;
import com.ugly.blog.dto.TableDataInfo;
import com.ugly.blog.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author JwZheng
 * @date 2021/4/9 22:56
 */
@Controller
@RequestMapping("api/system/article")
public class SysArticleController extends BaseController {

    @Autowired
    private PageService pageService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public TableDataInfo getTableData(@RequestParam(required = false, defaultValue = PageConstant.DEFAULT_PAGE_INDEX) Integer pageIndex,
                                      @RequestParam(required = false, defaultValue = PageConstant.DEFAULT_PAGE_SIZE) Integer pageSize) {
        Page<Article> page = pageService.getListByCondition(pageIndex, pageSize, null);
        return getDataTable(page);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public TableDataInfo getTableDataByCondition(@RequestParam(required = false, defaultValue = PageConstant.DEFAULT_PAGE_INDEX) Integer pageIndex,
                                                 @RequestParam(required = false, defaultValue = PageConstant.DEFAULT_PAGE_SIZE) Integer pageSize, String nickname, String title) {
        Article article = new Article();
        article.setTitle(title);
        User user = new User();
        user.setNickname(nickname);
        article.setUser(user);
        Page<Article> page = pageService.getListByCondition(pageIndex, pageSize, article);
        return getDataTable(page);
    }

}

package com.ugly.blog.controller.home;

import com.ugly.blog.constant.PageConstant;
import com.ugly.blog.domain.Article;
import com.ugly.blog.domain.Category;
import com.ugly.blog.dto.JSONResult;
import com.ugly.blog.dto.Page;
import com.ugly.blog.service.CategoryService;
import com.ugly.blog.service.PageService;
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
public class CategoryController {

    private final PageService pageService;

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(PageService pageService, CategoryService categoryService) {
        this.pageService = pageService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "category/{categoryId}", method = RequestMethod.GET)
    public String getArticle(@PathVariable("categoryId") Integer categoryId,
                             @RequestParam(required = false, defaultValue = PageConstant.DEFAULT_PAGE_INDEX) Integer pageIndex,
                             @RequestParam(required = false, defaultValue = PageConstant.DEFAULT_PAGE_SIZE) Integer pageSize, Model model) {

        Page<Article> page = pageService.getPageByCategory(pageIndex, pageSize, categoryId);
        model.addAttribute("articlePage", page);

        List<Category> categoryList = categoryService.getCategoryList(null);
        model.addAttribute("categoryList", categoryList);

        return "home/articlesByCategory";
    }

    @RequestMapping(value = "category/all", method = RequestMethod.GET)
    public String getArticle(
            @RequestParam(required = false, defaultValue = PageConstant.DEFAULT_PAGE_INDEX) Integer pageIndex,
            @RequestParam(required = false, defaultValue = PageConstant.DEFAULT_PAGE_SIZE) Integer pageSize, Model model) {

        Page<Article> page = pageService.getArticleDefaultPage(pageIndex, pageSize);
        model.addAttribute("articlePage", page);

        List<Category> categoryList = categoryService.getCategoryList(null);
        model.addAttribute("categoryList", categoryList);

        return "home/articlesByCategory";
    }

    @RequestMapping(value = "category/categoryName/{categoryId}", method = RequestMethod.GET)
    @ResponseBody
    public JSONResult getCategoryName(@PathVariable("categoryId") Integer categoryId) {
        JSONResult result = new JSONResult();
        Category category = categoryService.getCategoryById(categoryId);
        if (category == null) {
            result.setCode(404);
            result.setMsg("Tag Not Found");
        } else {
            result.setCode(200);
            result.setMsg("success");
            result.setData(category.getCategoryName());
        }
        return result;
    }

}

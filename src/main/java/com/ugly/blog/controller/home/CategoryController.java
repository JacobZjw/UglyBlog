package com.ugly.blog.controller.home;

import com.ugly.blog.config.AppConfig;
import com.ugly.blog.dto.Page;
import com.ugly.blog.entity.Article;
import com.ugly.blog.entity.Category;
import com.ugly.blog.service.CategoryService;
import com.ugly.blog.service.PageService;
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
public class CategoryController {
    @Autowired
    private PageService pageService;

    @Autowired
    private CategoryService categoryService;


    @RequestMapping(value = "category/{categoryId}", method = RequestMethod.GET)
    public String getArticle(@PathVariable("categoryId") Integer categoryId, Model model) {

        Page<Article> page = pageService.getPageByCategory(AppConfig.DEFAULT_PAGE_INDEX, AppConfig.DEFAULT_PAGE_SIZE, categoryId);
        model.addAttribute("articlePage", page);

        List<Category> categoryList = categoryService.getAllCategoryList();
        model.addAttribute("categoryList", categoryList);

        return "home/articlesByCategory";
    }

//    @RequestMapping(value = "category/{categoryId}", method = RequestMethod.GET)
//    public String pageByCategory(@PathVariable("categoryId") Integer categoryId,
//                                 @RequestParam(defaultValue = "1") Integer pageIndex,
//                                 @RequestParam(defaultValue = "10") Integer pageSize, Model model){
//        Page<Article> page = pageService.getPageByCategory(pageIndex, pageSize,categoryId);
//        model.addAttribute("articlePage", page);
//        return "Home/articlesByCategory";
//    }
}

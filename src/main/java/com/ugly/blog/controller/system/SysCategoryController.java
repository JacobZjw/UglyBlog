package com.ugly.blog.controller.system;

import com.ugly.blog.constant.PageConstant;
import com.ugly.blog.controller.common.BaseController;
import com.ugly.blog.domain.Category;
import com.ugly.blog.dto.AjaxResult;
import com.ugly.blog.dto.TableDataInfo;
import com.ugly.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/11 13:38
 */
@Controller
@RequestMapping("api/system/category")
public class SysCategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public TableDataInfo getList(@RequestParam(required = false, defaultValue = PageConstant.DEFAULT_PAGE_INDEX) Integer pageIndex,
                                 @RequestParam(required = false, defaultValue = PageConstant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                 String categoryName) {
        startPage(pageIndex, pageSize);
        List<Category> list = categoryService.getCategoryList(categoryName);
        return getDataTable(list);
    }

    @RequestMapping(value = "/list/all", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public TableDataInfo getList() {
        List<Category> list = categoryService.getCategoryList(null);
        return getDataTable(list);
    }

    @RequestMapping(value = "/delete/{categoryId}", method = RequestMethod.PUT, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public AjaxResult delete(@PathVariable("categoryId") Integer categoryId) {
        return toAjax(categoryService.deleteCategoryById(categoryId));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public AjaxResult update(@RequestBody Category category) {
        return toAjax(categoryService.updateOrInsertCategory(category));
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public AjaxResult insert(String categoryName) {
        return toAjax(categoryService.insertCategory(categoryName));
    }

}

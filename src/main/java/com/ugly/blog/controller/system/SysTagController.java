package com.ugly.blog.controller.system;

import com.ugly.blog.constant.PageConstant;
import com.ugly.blog.controller.BaseController;
import com.ugly.blog.domain.Tag;
import com.ugly.blog.dto.AjaxResult;
import com.ugly.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/11 15:52
 */
@Controller
@RequestMapping("api/system/tag")
public class SysTagController extends BaseController {

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @RolesAllowed("user")
    public AjaxResult getList(@RequestParam(required = false, defaultValue = PageConstant.DEFAULT_PAGE_INDEX) Integer pageIndex,
                              @RequestParam(required = false, defaultValue = PageConstant.DEFAULT_PAGE_SIZE) Integer pageSize,
                              String tagName) {
        startPage(pageIndex, pageSize);
        List<Tag> list = tagService.getTagList(tagName);
        return getDataTable(list);
    }

    @RequestMapping(value = "/list/all", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @RolesAllowed("user")
    public AjaxResult getList() {
        List<Tag> list = tagService.getTagList(null);
        return getDataTable(list);
    }

    @RequestMapping(value = "/delete/{tagId}", method = RequestMethod.PUT, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @RolesAllowed("user")
    public AjaxResult delete(@PathVariable("tagId") Integer tagId) {
        return toAjax(tagService.deleteTagById(tagId));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @RolesAllowed("user")
    public AjaxResult update(@RequestBody Tag tag) {
        return toAjax(tagService.updateOrInsertTag(tag));
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @RolesAllowed("user")
    public AjaxResult insert(String tagName) {
        return toAjax(tagService.insertTag(tagName));
    }
}

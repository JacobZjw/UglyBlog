package com.ugly.blog.controller.system;

import com.ugly.blog.constant.PageConstant;
import com.ugly.blog.controller.common.BaseController;
import com.ugly.blog.domain.User;
import com.ugly.blog.dto.AjaxResult;
import com.ugly.blog.dto.TableDataInfo;
import com.ugly.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/13 15:05
 */
@Controller
@RequestMapping("api/system/user")
public class SysUserController extends BaseController {

    private final UserService userService;

    @Autowired
    public SysUserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public TableDataInfo getTableDataByCondition(@RequestParam(required = false, defaultValue = PageConstant.DEFAULT_PAGE_INDEX) Integer pageIndex,
                                                 @RequestParam(required = false, defaultValue = PageConstant.DEFAULT_PAGE_SIZE) Integer pageSize,
                                                 User user) {
        startPage(pageIndex, pageSize);
        List<User> list = userService.getListByCondition(user);
        return getDataTable(list);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getDetails(@PathVariable("userId") Integer userId) {
        return toAjax(userService.getDetails(userId));
    }

    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResult delete(@PathVariable("userId") Integer userId) {
        return toAjax(userService.delete(userId));
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult insert(@RequestBody User user) {
        return toAjax(userService.insert(user));
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult update(@RequestBody User user) {
        return toAjax(userService.update(user));
    }

    @RequestMapping(value = "/role/{userId}/switch", method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResult switchRole(@PathVariable("userId") Integer userId) {
        return toAjax(userService.switchRole(userId));
    }

    @RequestMapping(value = "/status/{userId}/switch", method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResult switchStatus(@PathVariable("userId") Integer userId) {
        return toAjax(userService.switchStatus(userId));
    }


}

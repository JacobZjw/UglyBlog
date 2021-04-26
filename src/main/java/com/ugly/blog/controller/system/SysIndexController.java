package com.ugly.blog.controller.system;

import com.ugly.blog.controller.BaseController;
import com.ugly.blog.dto.AjaxResult;
import com.ugly.blog.util.JsonResourceUtils;
import com.ugly.blog.util.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.security.RolesAllowed;

/**
 * @author JwZheng
 * @date 2021/3/31 15:04
 */
@Controller
public class SysIndexController extends BaseController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/sys")
    public String sys() {
        return "index";
    }

    @RequestMapping("/api/system/nickname")
    @ResponseBody
    public AjaxResult getCurUsername(){
        String nickname = SecurityUtils.getCurUser().getUser().getNickname();
        AjaxResult success = AjaxResult.success();
        success.put("nickname",nickname);
        return success;
    }


    @RequestMapping("/api/system/id")
    @ResponseBody
    public AjaxResult getCurUserId(){
        Integer id = SecurityUtils.getCurUser().getId();
        AjaxResult success = AjaxResult.success();
        success.put("userId",id);
        return success;
    }

    @RequestMapping("/api/system/init")
    @ResponseBody
    @RolesAllowed("user")
    public Object init() {
        if (SecurityUtils.isAdmin()) {
            return JsonResourceUtils.getJsonObjFromResource("/init/adminInit.json");
        } else {
            return JsonResourceUtils.getJsonObjFromResource("/init/userInit.json");
        }
    }

    @RequestMapping("/api/system/clear")
    @ResponseBody
    public AjaxResult clear() {
        return AjaxResult.success();
    }
}

package com.ugly.blog.controller.system;

import com.ugly.blog.constant.Constants;
import com.ugly.blog.controller.BaseController;
import com.ugly.blog.dto.AjaxResult;
import com.ugly.blog.dto.LoginBody;
import com.ugly.blog.service.UserLoginService;
import com.ugly.blog.service.UserService;
import com.ugly.blog.util.JsonResourceUtils;
import com.ugly.blog.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.security.RolesAllowed;

/**
 * @author JwZheng
 * @date 2021/3/31 15:04
 */
@Controller
public class SysIndexController extends BaseController {

    private final UserService userService;

    private final UserLoginService userLoginService;


    @Autowired
    public SysIndexController(UserService userService, UserLoginService userLoginService) {
        this.userService = userService;
        this.userLoginService = userLoginService;
    }

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


    @RequestMapping(value = "/loginVerify", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult loginVerify(LoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        String token = userLoginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode());
        ajax.put(Constants.TOKEN_HEADER, token);
        return ajax;
    }
}

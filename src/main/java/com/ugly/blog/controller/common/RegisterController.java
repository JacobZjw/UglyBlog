package com.ugly.blog.controller.common;

import com.ugly.blog.constant.HttpStatus;
import com.ugly.blog.dto.AjaxResult;
import com.ugly.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author JwZheng
 * @date 2021/4/17 15:32
 */
@Controller
public class RegisterController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping("/register")
    public String registerPage() {
        return "register";
    }

    @RequestMapping("/denied")
    @ResponseBody
    public AjaxResult accessDenied() {
        return AjaxResult.error(HttpStatus.FORBIDDEN, "您没有权限操作");
    }
}

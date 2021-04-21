package com.ugly.blog.controller.common;

import com.ugly.blog.constant.Constants;
import com.ugly.blog.controller.BaseController;
import com.ugly.blog.dto.AjaxResult;
import com.ugly.blog.dto.LoginBody;
import com.ugly.blog.service.UserLoginService;
import com.ugly.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author JwZheng
 * @date 2021/3/31 15:04
 */
@Controller
public class LoginController extends BaseController {

    private final UserService userService;

    private final UserLoginService userLoginService;

    @Autowired
    public LoginController(UserService userService, UserLoginService userLoginService) {
        this.userService = userService;
        this.userLoginService = userLoginService;
    }


    @RequestMapping("/login")
    public String loginPage() {
        return "login";
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

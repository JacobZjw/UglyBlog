package com.ugly.blog.controller.system;

import com.ugly.blog.constant.FileConstant;
import com.ugly.blog.controller.BaseController;
import com.ugly.blog.dto.AjaxResult;
import com.ugly.blog.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;

/**
 * @author JwZheng
 * @date 2021/4/13 21:37
 */
@Controller
@RequestMapping("api/system/upload")
public class SysFileUploadController extends BaseController {

    private final FileService fileService;

    @Autowired
    public SysFileUploadController(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping(value = "/avatar", method = RequestMethod.POST)
    @ResponseBody
    @RolesAllowed("user")
    public AjaxResult uploadAvatar(@RequestParam MultipartFile file) {
        return fileService.saveFile(FileConstant.USER_AVATAR_PATH, file);
    }

    @RequestMapping(value = "/article", method = RequestMethod.POST)
    @ResponseBody
    @RolesAllowed("user")
    public AjaxResult uploadFile(@RequestParam MultipartFile file) {
        return fileService.saveFile(FileConstant.ARTICLE_IMG_PATH, file);
    }


}

package com.ugly.blog.service;

import com.ugly.blog.dto.AjaxResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author JwZheng
 * @date 2021/4/14 13:31
 */
public interface FileService {
    /**
     * 保存文件
     *
     * @param path 保存路径
     * @param file 文件
     * @return 文件路径
     */
    AjaxResult saveFile(String path, MultipartFile file);
}

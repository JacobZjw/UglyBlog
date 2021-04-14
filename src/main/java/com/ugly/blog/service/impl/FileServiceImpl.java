package com.ugly.blog.service.impl;

import com.ugly.blog.constant.FileConstant;
import com.ugly.blog.constant.HttpStatus;
import com.ugly.blog.dto.AjaxResult;
import com.ugly.blog.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Calendar;

/**
 * @author JwZheng
 * @date 2021/4/14 13:33
 */
@Service
public class FileServiceImpl implements FileService {

    @Override
    public AjaxResult saveFile(String path, MultipartFile file) {
        if (file == null) {
            return AjaxResult.error(HttpStatus.BAD_REQUEST, "上传文件有误");
        }
        String filename = file.getOriginalFilename();
        assert filename != null;
        String name = filename.substring(0, filename.indexOf("."));
        String suffix = filename.substring(filename.lastIndexOf("."));
        if (!FileConstant.ALLOW_SUFFIX.contains(suffix)) {
            return AjaxResult.error(HttpStatus.BAD_REQUEST, "不允许上传该后缀的文件！");
        }
        //创建年月文件夹
        Calendar date = Calendar.getInstance();
        File dateDirs = new File(date.get(Calendar.YEAR)
                + File.separator + (date.get(Calendar.MONTH) + 1));
        //目标文件
        File descFile = new File(path + File.separator + dateDirs + File.separator + filename);

        int i = 1;
        //若文件存在重命名
        String newFilename = filename;
        while (descFile.exists()) {
            newFilename = name + "(" + i + ")" + suffix;
            String parentPath = descFile.getParent();
            descFile = new File(parentPath + File.separator + newFilename);
            i++;
        }
        //判断目标文件所在的目录是否存在
        if (!descFile.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            descFile.getParentFile().mkdirs();
        }

        //将内存中的数据写入磁盘
        try {
            file.transferTo(descFile);
        } catch (Exception e) {
            return AjaxResult.error(HttpStatus.ERROR, "上传失败，请稍后再试");
        }
        //完整的url
        String fileUrl = path + File.separator + dateDirs + File.separator + newFilename;

        return new AjaxResult(HttpStatus.SUCCESS, null, fileUrl);
    }
}

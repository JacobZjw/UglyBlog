package com.ugly.blog.exception.file;

import com.ugly.blog.exception.BaseException;

/**
 * 文件信息错误异常
 *
 * @author JwZheng
 * @date 2021/4/14 16:03
 */
public class FileException extends BaseException {

    private static final long serialVersionUID = -4926547174499892357L;

    public FileException(String code, Object[] args) {
        super("file", code, args, null);
    }
}

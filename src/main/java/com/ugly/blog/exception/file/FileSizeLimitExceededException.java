package com.ugly.blog.exception.file;

/**
 * 文件大小限制异常
 *
 * @author JwZheng
 * @date 2021/4/14 16:04
 */
public class FileSizeLimitExceededException extends FileException {


    private static final long serialVersionUID = -5081824436737180621L;

    public FileSizeLimitExceededException(long defaultMaxSize) {
        super("upload.exceed.maxSize", new Object[]{defaultMaxSize});
    }
}

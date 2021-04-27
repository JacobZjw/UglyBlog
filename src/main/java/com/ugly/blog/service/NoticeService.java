package com.ugly.blog.service;

import com.ugly.blog.domain.Notice;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/27 14:28
 */
public interface NoticeService {


    /**
     * 新增公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    int insert(Notice notice);

    /**
     * 根据公告ID删除公告
     */
    int delete(Integer noticeId);

    /**
     * 修改公告信息
     *
     * @param notice 公告信息
     * @return 结果
     */
    int update(Notice notice);

    /**
     * 根据公告ID查询公告信息
     *
     * @param noticeId 公告ID
     * @return 公告信息
     */
    Notice getById(Integer noticeId);

    /**
     * 获取所有公告
     *
     * @return 公告信息列表
     */
    List<Notice> getAll();

    /**
     * 获取所有未删除的公告
     *
     * @return 公告信息列表
     */
    List<Notice> getAllIsNotDel();

    /**
     * 校验操作权限
     * @param noticeId 公告ID
     */
    void checkAuthorized(Integer noticeId);
}

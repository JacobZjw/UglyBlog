package com.ugly.blog.service;

import com.ugly.blog.BaseTest;
import com.ugly.blog.domain.Notice;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author JwZheng
 * @date 2021/4/27 14:36
 */
public class NoticeServiceTest extends BaseTest {

    @Autowired
    private NoticeService noticeService;

    @Test
    public void getById() {
    }

    @Test
    public void getAll() {
        noticeService.getAll().forEach(System.out::println);
    }

    @Test
    public void getAllIsNotDel() {
        noticeService.getAllIsNotDel().forEach(System.out::println);
    }

    @Test
    public void insert() {
    }

    @Test
    public void delete() {
        noticeService.delete(4);
    }

    @Test
    public void update() {
        Notice notice = new Notice();
        notice.setNoticeId(4);
        notice.setTitle("新增公告版模块");
        noticeService.update(notice);
    }
}
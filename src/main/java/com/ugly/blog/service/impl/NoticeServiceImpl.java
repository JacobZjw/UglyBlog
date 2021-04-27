package com.ugly.blog.service.impl;

import com.ugly.blog.constant.Constants;
import com.ugly.blog.domain.Notice;
import com.ugly.blog.mapper.NoticeMapper;
import com.ugly.blog.mapper.UserMapper;
import com.ugly.blog.service.NoticeService;
import com.ugly.blog.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/27 14:29
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    private final NoticeMapper noticeMapper;
    private final UserMapper userMapper;

    @Autowired
    public NoticeServiceImpl(NoticeMapper noticeMapper, UserMapper userMapper) {
        this.noticeMapper = noticeMapper;
        this.userMapper = userMapper;
    }

    @Override
    public int insert(Notice notice) {
        notice.setAuthorId(SecurityUtils.getCurUserId());
        return noticeMapper.insert(notice);
    }

    @Override
    public int delete(Integer noticeId) {
        return noticeMapper.delete(noticeId);
    }

    @Override
    public int update(Notice notice) {
        return noticeMapper.update(notice);
    }

    @Override
    public Notice getById(Integer noticeId) {
        return noticeMapper.getById(noticeId);
    }

    @Override
    public List<Notice> getAll() {
        List<Notice> list = noticeMapper.getByCondition(null);
        for (Notice notice : list) {
            notice.setAuthorNickname(userMapper.getById(notice.getAuthorId()).getNickname());
        }
        return list;
    }

    @Override
    public List<Notice> getAllIsNotDel() {
        Notice notice = new Notice();
        notice.setIsDel(Constants.NOTICE_IS_NOT_DEL);
        return noticeMapper.getByCondition(notice);
    }

    @Override
    public void checkAuthorized(Integer noticeId) {
        Notice notice = noticeMapper.getById(noticeId);
        if (!SecurityUtils.isAdmin() && !SecurityUtils.getCurUserId().equals(notice.getAuthorId())) {
            throw new AccessDeniedException("权限不足");
        }
    }
}

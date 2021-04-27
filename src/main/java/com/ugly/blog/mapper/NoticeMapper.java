package com.ugly.blog.mapper;

import com.ugly.blog.domain.Notice;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/4/27 14:02
 */
@Repository
public interface NoticeMapper {

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
     * @param noticeId 公告ID
     * @return 公告信息
     */
    Notice getById(Integer noticeId);

    /**
     * 根据条件获取公告列表
     * @param notice 公告信息
     * @return 公告信息列表
     */
    List<Notice> getByCondition(Notice notice);
}

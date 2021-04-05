package com.ugly.blog.service;

import com.ugly.blog.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

/**
 * @author JwZheng
 * @date 2021/3/31 18:23
 */
public interface UserService {


    /**
     * 根据用户名或Email获得用户
     *
     * @param str 用户名或Email
     * @return 用户
     */
    User getUserByNameOrEmail(String str);



    /**
     * 更新
     *
     * @param user 用户
     * @return 影响行数
     */
    int update(User user);
}

package com.ugly.blog.service.impl;

import com.ugly.blog.domain.User;
import com.ugly.blog.mapper.UserMapper;
import com.ugly.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author JwZheng
 * @date 2021/3/31 18:35
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public User getUserByNameOrEmail(String str) {
        return userMapper.getUserByNameOrEmail(str);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }
}

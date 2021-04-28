package com.ugly.blog.service.impl;

import com.ugly.blog.constant.HttpStatus;
import com.ugly.blog.constant.UserConstant;
import com.ugly.blog.domain.User;
import com.ugly.blog.exception.CustomException;
import com.ugly.blog.mapper.UserMapper;
import com.ugly.blog.service.UserService;
import com.ugly.blog.util.SecurityUtils;
import com.ugly.blog.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return userMapper.getByNameOrEmail(str);
    }

    @Override
    public List<User> getListByCondition(User user) {
        return userMapper.getListByCondition(user);
    }

    @Override
    public boolean check(User user) {
        return userMapper.check(user) > 0;
    }

    @Override
    public String checkUsernameUnique(String username) {
        if (userMapper.getByName(username) != null) {
            return UserConstant.NOT_UNIQUE;
        }
        return UserConstant.UNIQUE;
    }

    @Override
    public String checkEmailUnique(String email) {
        if (userMapper.getByEmail(email) != null) {
            return UserConstant.NOT_UNIQUE;
        }
        return UserConstant.UNIQUE;
    }

    @Override
    public void checkAuthority(Integer userId) {
        if (Utils.isNull(userId)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "参数异常");
        }
        if (userId.equals(1)) {
            throw new CustomException(HttpStatus.FORBIDDEN, "不允许操作超级管理员账户");
        }
        if (!userId.equals(SecurityUtils.getCurUserId()) && !SecurityUtils.isAdmin()) {
            throw new AccessDeniedException("没有权限");
        }
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int delete(Integer userId) {
        return userMapper.delete(userId);
    }

    @Override
    public User getDetails(Integer userId) {
        return userMapper.getById(userId);
    }

    @Override
    public int getUserCount() {
        return userMapper.getTotalCount();
    }

    @Override
    public int switchRole(Integer userId) {
        User user = getDetails(userId);
        if (user == null) {
            return 0;
        }
        user.setRole(1 - user.getRole());
        return userMapper.update(user);
    }

    @Override
    public int switchStatus(Integer userId) {
        User user = getDetails(userId);
        if (user == null) {
            return 0;
        }
        user.setStatus(1 - user.getStatus());
        return userMapper.update(user);
    }
}

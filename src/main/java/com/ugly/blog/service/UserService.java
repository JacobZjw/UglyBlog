package com.ugly.blog.service;

import com.ugly.blog.domain.User;

import java.util.List;

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
     * 根据条件查找用户列表
     *
     * @param user 用户信息
     * @return 用户对象列表
     */
    List<User> getListByCondition(User user);

    /**
     * 验证用户密码是否匹配
     *
     * @param user 用户信息
     * @return 结果
     */
    boolean check(User user);

    /**
     * 校验用户名称是否唯一
     *
     * @param username 用户名称
     * @return 结果
     */
    String checkUsernameUnique(String username);


    /**
     * 校验邮箱是否唯一
     *
     * @param email 邮箱
     * @return 结果
     */
    String checkEmailUnique(String email);

    /**
     * 校验是否有权限操作超级管理员信息
     *
     * @param userId 用户ID
     */
    void checkUserAllow(Integer userId);

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    int update(User user);

    /**
     * 新增用户
     *
     * @param user 用户信息
     * @return 结果
     */
    int insert(User user);

    /**
     * 删除用户
     *
     * @param userId 用户Id
     * @return 结果
     */
    int delete(Integer userId);

    /**
     * 获取用户详细信息
     *
     * @param userId 用户Id
     * @return 结果
     */
    User getDetails(Integer userId);


    /**
     * 切换角色
     *
     * @param userId 用户ID
     * @return 结果
     */
    int switchRole(Integer userId);

    /**
     * 切换状态
     *
     * @param userId 用户ID
     * @return 结果
     */
    int switchStatus(Integer userId);
}

package com.ugly.blog.mapper;

import com.ugly.blog.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/3/31 18:23
 */
@Repository
public interface UserMapper {

    /**
     * 新增用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    int insert(User user);

    /**
     * 验证用户密码是否匹配
     *
     * @param user 用户信息
     * @return 结果
     */
    int check(User user);

    /**
     * 根据用户ID删除用户信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    int delete(@Param("userId") Integer userId);

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    int update(User user);

    /**
     * 根据ID查询用户信息
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    User getById(Integer userId);

    /**
     * 查询所有用户信息
     *
     * @return 用户对象列表
     */
    List<User> getList();

    /**
     * 根据条件查找用户列表
     *
     * @param user 用户信息
     * @return 用户对象列表
     */
    List<User> getListByCondition(User user);

    /**
     * 根据用户名或邮箱查询用户信息
     *
     * @param str 用户名或邮箱
     * @return 用户信息对象
     */
    User getByNameOrEmail(@Param("str") String str);

    /**
     * 根据用户名查询用户信息
     *
     * @param name 用户名
     * @return 用户信息对象
     */
    User getByName(@Param("name") String name);

    /**
     * 根据邮箱查询用户信息
     *
     * @param email 邮箱
     * @return 用户信息对象
     */
    User getByEmail(@Param("email") String email);

}

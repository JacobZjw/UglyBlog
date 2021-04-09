package com.ugly.blog.mapper;

import com.ugly.blog.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JwZheng
 * @date 2021/3/31 18:23
 */
@Repository
public interface UserMapper {


    /**
     * 根据ID删除
     *
     * @param userId 用户ID
     * @return 影响行数
     */
    @ResultMap("userMap")
    @Delete("DELETE FROM user WHERE user_id=#{userId}")
    int deleteById(@Param("userId") Integer userId);

    /**
     * 添加
     *
     * @param user 用户
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 根据ID查询
     *
     * @param userId 用户ID
     * @return 用户
     */
    @ResultMap("userMap")
    @Select("SELECT * FROM user WHERE user_id = #{userId}")
    User getUserById(Integer userId);

    /**
     * 更新
     *
     * @param user 用户
     * @return 影响行数
     */
    @ResultMap("userMap")
    int update(User user);


    /**
     * 获得用户列表
     *
     * @return 用户列表
     */
    @Results(id = "userMap", value = {
            @Result(property = "userId", column = "user_id", id = true),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userPwd", column = "user_pwd"),
            @Result(property = "userNickname", column = "user_nickname"),
            @Result(property = "userEmail", column = "user_email"),
            @Result(property = "userAvatar", column = "user_avatar"),
            @Result(property = "userLastLoginIp", column = "user_last_login_ip"),
            @Result(property = "userRegisterTime", column = "user_register_time"),
            @Result(property = "userLastLoginTime", column = "user_last_login_time"),
            @Result(property = "userStatus", column = "user_status"),
            @Result(property = "userRole", column = "user_role")
    })
    @Select("SELECT * FROM user")
    List<User> getUserList();


    /**
     * 根据用户名或Email获得用户
     *
     * @param str 用户名或Email
     * @return 用户
     */
    @ResultMap("userMap")
    @Select("SELECT * FROM user WHERE user_email=#{str} OR user_name=#{str}")
    User getUserByNameOrEmail(@Param("str") String str);

    /**
     * 根据用户名查用户
     *
     * @param name 用户名
     * @return 用户
     */
    @ResultMap("userMap")
    @Select("SELECT * FROM user WHERE user_name = #{name}")
    User getUserByName(@Param("name") String name);

    /**
     * 根据Email查询用户
     *
     * @param email 邮箱
     * @return 用户
     */
    @ResultMap("userMap")
    @Select("SELECT * FROM user WHERE user_email = #{email}")
    User getUserByEmail(@Param("email") String email);

}

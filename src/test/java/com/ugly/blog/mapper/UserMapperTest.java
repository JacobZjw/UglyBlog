package com.ugly.blog.mapper;

import com.ugly.blog.BaseTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author JwZheng
 * @date 2021/4/9 19:12
 */
public class UserMapperTest extends BaseTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    @Ignore
    public void insert() {
    }

    @Test
    @Ignore
    public void deleteById() {
    }

    @Test
    @Ignore
    public void update() {
    }

    @Test
    public void getUserById() {
        assertNotNull(userMapper.getById(1));
    }

    @Test
    public void getUserList() {
        assertNotEquals(0, userMapper.getList().size());
    }

    @Test
    public void getUserByNameOrEmail() {
        System.out.println(userMapper.getByNameOrEmail("admin"));
        assertNotNull(userMapper.getByNameOrEmail("admin"));
        assertNotNull(userMapper.getByNameOrEmail("admin@admin.com"));
    }

    @Test
    public void getUserByName() {
        assertNotNull(userMapper.getByName("admin"));
    }

    @Test
    public void getUserByEmail() {
        assertNotNull(userMapper.getByEmail("admin@admin.com"));
    }

    @Test
    public void getListByCondition() {
        userMapper.getListByCondition(null).forEach(System.out::println);
    }
}
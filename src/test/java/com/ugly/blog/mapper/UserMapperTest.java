package com.ugly.blog.mapper;

import com.ugly.blog.domain.User;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author JwZheng
 * @date 2021/3/31 21:30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:Spring-mvc.xml", "classpath:applicationContext.xml"})
public class UserMapperTest extends TestCase {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testDeleteById() {
    }

    @Test
    public void testInsert() {
    }

    @Test
    public void testGetUserById() {
        assertNotNull(userMapper.getUserById(1));
    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void testGetUserList() {
        userMapper.getUserList().forEach(System.out::println);
    }

    @Test
    public void testGetUserByNameOrEmail() {
        assertNotNull(userMapper.getUserByNameOrEmail("admin"));
    }

    @Test
    public void testGetUserByName() {
        User admin = userMapper.getUserByName("admin");
        System.out.println(admin);
        assertNotNull(admin);
    }

    @Test
    public void testGetUserByEmail() {
        User admin = userMapper.getUserByEmail("admin@liuyanzhao.com");
        System.out.println(admin);
        assertNotNull(admin);
    }
}
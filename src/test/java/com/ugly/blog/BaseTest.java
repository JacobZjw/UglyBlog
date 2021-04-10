package com.ugly.blog;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author JwZheng
 * @date 2021/4/2 18:57
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/Spring-mvc.xml", "classpath:spring/Spring-context.xml", "classpath*:mapper/*.xml"})
public class BaseTest {

}

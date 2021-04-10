package com.ugly.blog;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author JwZheng
 * @date 2021/4/2 18:57
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("src/main/resources")
@ContextConfiguration({"classpath:spring/Spring-mvc.xml", "classpath:spring/Spring-context.xml"})
public class BaseTest {

}

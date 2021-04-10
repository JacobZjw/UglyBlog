package com.ugly.blog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * @author JwZheng
 * @date 2021/3/31 18:03
 */
@Configuration
@Component
@ComponentScan(basePackages = "com.ugly.blog", excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class))
@PropertySource(value = {"classpath:application.properties"}, ignoreResourceNotFound = false)
public class AppConfig {


    public static final Integer SIDEBAR_CATEGORY_NUM = 4;

    public static final Integer SIDEBAR_HOT_ARTICLE_NUM = 5;

}

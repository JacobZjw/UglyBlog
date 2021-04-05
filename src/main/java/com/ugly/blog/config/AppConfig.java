package com.ugly.blog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author JwZheng
 * @date 2021/3/31 18:03
 */
@Configuration
@ComponentScan(basePackages = "com.ugly.blog", excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class))
public class AppConfig {

}
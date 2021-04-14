package com.ugly.blog.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
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

    @Value("${118n.msgPath}")
    public String msgPath = "classpath:118n/messages.properties";


    @Bean("messageSource")
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(msgPath);
        messageSource.setCacheSeconds(60); //reload messages every 10 seconds
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

}

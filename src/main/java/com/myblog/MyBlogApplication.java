package com.myblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author wenzhihuai
 * @since 2020/2/21 17:44
 */

@EnableCaching
@EnableScheduling
@SpringBootApplication
@MapperScan({"com.myblog.dao"})
public class MyBlogApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MyBlogApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MyBlogApplication.class, args);
    }
}

package com.myblog;

import org.mybatis.spring.annotation.MapperScan;
import org.python.util.PythonInterpreter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Properties;

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
        Properties props = new Properties();
        props.put("python.home", "/usr/bin/python3");
        props.put("python.console.encoding", "UTF-8");
        props.put("python.security.respectJavaAccessibility", "false");
        props.put("python.import.site", "false");
        Properties preprops = System.getProperties();
        PythonInterpreter.initialize(preprops, props, new String[0]);

        SpringApplication.run(MyBlogApplication.class, args);
    }
}

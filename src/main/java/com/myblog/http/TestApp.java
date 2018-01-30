package com.myblog.http;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/8/15 13:18
 * Description:
 */
public class TestApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:bean-*.xml");
        System.out.println(context);

        HttpService httpService = context.getBean("httpService",
                HttpService.class);
        System.out.println(httpService);
        try {
            // Map<String, String> maps = new HashMap<String, String>();
            // maps.put("wd", "java");
            // String string = httpService.doGet("http://www.baidu.com/s");
            // System.out.println(string);

            Map<String, Object> maps = new HashMap<String, Object>();
            maps.put("wd", "java");
            String string = httpService.doPost(
                    "http://localhost:8080/ssss/HaHaServlet", maps);
            System.out.println(string);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
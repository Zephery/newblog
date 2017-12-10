package com.myblog.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * tomcat的单实例多线程测试
 *
 * @author Zephery
 * @since 2017/12/9 16:49
 */
public class SingleToMany {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(SingleToMany.class);
    private static SingleToMany instance = null;
    private String name;

    public SingleToMany() {
    }

    public SingleToMany(String name) {
        this.name = name;
    }

    public synchronized static SingleToMany getInstance() throws Exception {
        if (instance == null) {
            String str = "";
            ClassLoader loader = new URLClassLoader(new URL[]{new URL(str)});
            instance = (SingleToMany) loader.loadClass("").newInstance();
        }
        try {
            Thread.sleep(10 * 1000);
        } catch (Exception e) {
        }
        return instance;
    }


    public void test() {
        System.out.println(SingleToMany.instance.name);
        System.out.println(instance.toString());
//        synchronized (this) {
//            instance = new SingleToMany(DateTime.now().toString("HH:mm:ss SSS"));
//        }
    }
}
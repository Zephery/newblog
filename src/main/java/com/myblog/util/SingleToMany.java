package com.myblog.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public SingleToMany() {
    }

    public synchronized static SingleToMany getInstance() {
        if (instance == null) {
            instance = new SingleToMany();
        }
        try {
            Thread.sleep(10 * 1000);
        } catch (Exception e) {
        }
        return instance;
    }

    public void test() {
//        try {
//            Thread.sleep(20 * 1000);
//        } catch (Exception e) {
//            logger.error("", e);
//        }
    }
}
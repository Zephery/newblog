package com.myblog.util;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Zephery
 * @since 2017/11/18 18:36
 */
public class SpringTest {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(SpringTest.class);

    @Before
    public void init() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
    }

    @Test
    public void testGetUserById() {
        PythonUtil.executeMyWeiBo();
    }
}
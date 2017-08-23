package com.myblog.druid;

import com.alibaba.druid.pool.DruidDataSourceStatLogger;
import com.alibaba.druid.pool.DruidDataSourceStatValue;
import com.alibaba.druid.support.logging.Log;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/8/23 11:06
 * Description:
 */
public class MyStatLogger implements DruidDataSourceStatLogger {
    @Override
    public void log(DruidDataSourceStatValue var1) {

    }

    @Override
    public void configFromProperties(Properties var1) {

    }

    @Override
    public void setLogger(Log var1) {

    }

    @Override
    public void setLoggerName(String var1) {

    }
}
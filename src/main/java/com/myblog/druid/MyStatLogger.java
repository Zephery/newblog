package com.myblog.druid;

import com.alibaba.druid.pool.DruidDataSourceStatLogger;
import com.alibaba.druid.pool.DruidDataSourceStatLoggerAdapter;
import com.alibaba.druid.pool.DruidDataSourceStatValue;
import com.alibaba.druid.support.logging.Log;
import com.myblog.dao.IMongoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/8/23 11:06
 * Description:阿里druid数据库监控，记录日志，有空再写
 */
@Component
public class MyStatLogger extends DruidDataSourceStatLoggerAdapter implements DruidDataSourceStatLogger {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(MyStatLogger.class);
    @Resource
    private IMongoDao mongoDao;

    @Override
    public void log(DruidDataSourceStatValue var1) {
        //TODO 似乎没有啥用
//        Gson gson = new Gson();
//        String tojson = gson.toJson(var1);
//        mongoDao.insert(tojson, "druidlog");
    }

    @Override
    public void configFromProperties(Properties var1) {
        System.out.println();
    }

    @Override
    public void setLogger(Log var1) {
        System.out.println();
    }

    @Override
    public void setLoggerName(String var1) {
        System.out.println();
    }
}
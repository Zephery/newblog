package com.myblog.util;

import lombok.extern.apachecommons.CommonsLog;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2018/2/13 9:52
 * Description:
 */
@CommonsLog
@SuppressWarnings("unchecked")
public class LogUtil {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);
    @Resource
    private static RedisTemplate redisTemplate;

    public static void record(String key, String value) {
        redisTemplate.opsForHash().put("log", key, value + DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
    }
}
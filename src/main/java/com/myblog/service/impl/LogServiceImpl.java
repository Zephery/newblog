package com.myblog.service.impl;

import com.myblog.service.ILogService;
import com.myblog.util.IPUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2018/2/13 16:33
 * Description:
 */
@Service("logService")
@SuppressWarnings("unchecked")
public class LogServiceImpl implements ILogService {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);
    @Resource
    private RedisTemplate redisTemplate;
    private static final String IP = IPUtils.getServerIp();

    @Override
    public void record(String key, String value) {
        redisTemplate.opsForHash().put("log", key + IP, value + DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
    }
}
package com.myblog.service.impl;

import com.myblog.service.ILogService;
import com.myblog.util.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2018/2/13 16:33
 * Description:
 */
@Slf4j
@Service("logService")
@SuppressWarnings("unchecked")
public class LogServiceImpl implements ILogService {
    @Resource
    private RedisTemplate redisTemplate;
    private static final String IP = IPUtils.getServerIp();

    @Override
    public void record(String key, String value) {
        redisTemplate.opsForHash().put("log", key + IP, value + DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
    }
}
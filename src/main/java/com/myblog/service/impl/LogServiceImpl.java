package com.myblog.service.impl;

import com.google.common.base.Strings;
import com.google.gson.JsonParser;
import com.myblog.service.ILogService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


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
    @Resource
    private RestTemplate restTemplate;

    @Override
    public void record(String key, String value) {
        String ip = "";
        String url = "https://myipip.net/";
        try {
            String response = restTemplate.getForObject(url, String.class);
            if (Strings.isNullOrEmpty(response)) {
                return;
            }
            String resp = response.replaceAll("\n", "");
            ip = JsonParser.parseString(resp).getAsJsonObject().get("ip").getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        redisTemplate.opsForHash().put("log", key + ip, value + DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
    }
}
package com.myblog.task;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.python.util.PythonInterpreter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zepherywen
 * @since 2020/12/19
 */
@Slf4j
@Component
public class BaiduTask {

    @Scheduled(fixedDelay = 1000)
    private void reaew() throws IOException {
        log.info("aaa");
        aaaa();

    }

    private void aaaa() throws IOException {

        String path = "./getbaidu.py";

        InputStream stream = getClass().getClassLoader().getResourceAsStream("pythonfiles/getbaidu.py");
        File targetFile = new File(path);
        FileUtils.copyInputStreamToFile(stream, targetFile);


        Process proc;
        try {
            proc = Runtime.getRuntime().exec("python3 " + path);// 执行py文件
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}

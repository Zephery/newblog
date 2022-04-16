package com.myblog.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zepherywen
 * @since 2020/12/19
 */
@Slf4j
@Component
public class BaiduTask {

    @Scheduled(initialDelay = 20 * 1000, fixedDelay = 24 * 3600 * 1000)
    private void refreshData() {
        log.info("refreshData");
        run();
    }

    @Scheduled(cron = "0 0 5 * * ?")
    private void ling() {
        log.info("refreshData");
        run();
    }

    private void run() {
        String path = "/data/logs/newblog/getbaidu.py";

        Process proc;
        try {
            proc = Runtime.getRuntime().exec("python3 " + path);// 执行py文件
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                log.info(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}

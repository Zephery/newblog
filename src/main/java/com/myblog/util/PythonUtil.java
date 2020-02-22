package com.myblog.util;

import com.myblog.common.Common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Zephery
 * @since 2017/11/18 16:47
 */
public class PythonUtil {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(PythonUtil.class);
    private static final String BATH_PATH = System.getProperty("myblog.path");


    public static void executeMyWeiBo() {
        try {
            String pythonweiboPath;
            if (WinOrLinux.isWin()) {
                pythonweiboPath = BATH_PATH + "WEB-INF\\classes\\pythonfiles\\myweibo.py";
            } else {
                pythonweiboPath = BATH_PATH + "WEB-INF/classes/pythonfiles/myweibo.py";

            }
            String logPath = ">>" + BATH_PATH + "WEB-INF/classes/pythonfiles/myweibo.log";
            String command = Common.PYTHON_PATH + " " + pythonweiboPath + logPath;
            logger.info(command);
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "utf-8"));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
                logger.info(line);
            }
            process.waitFor();
        } catch (Exception e) {
            logger.error("微博脚本执行失败", e);
        }
    }

    public static void executeGetBaidu() {
        try {
            String pythonweiboPath;
            if (WinOrLinux.isWin()) {
                pythonweiboPath = BATH_PATH + "WEB-INF\\classes\\pythonfiles\\getbaidu.py";
            } else {
                pythonweiboPath = BATH_PATH + "WEB-INF/classes/pythonfiles/getbaidu.py";

            }
            logger.info(pythonweiboPath);
            Process process = Runtime.getRuntime().exec(Common.PYTHON_PATH + " " + pythonweiboPath);
            process.waitFor();
        } catch (Exception e) {
            logger.error("百度脚本执行失败", e);
        }
    }

    public static void distributelearning() {

    }

    public static void main(String[] args) throws Exception {
        PythonUtil.executeMyWeiBo();
    }
}
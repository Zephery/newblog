package com.myblog.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 重启tomcat
 *
 * @author Zephery
 * @since 2017/12/7 9:19
 */
public class BashUtil {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(BashUtil.class);

    private static BashUtil bashUtil = null;

    public BashUtil() {

    }

    public static BashUtil getInstance() {
        if (bashUtil == null) {
            bashUtil = createInstance();
        }
        return bashUtil;
    }

    private synchronized static BashUtil createInstance() {
        if (bashUtil == null) {
            bashUtil = new BashUtil();
        }
        return bashUtil;
    }

    public void executeRestartProject() {
        Process process = null;
        String filePath = System.getProperty("myblog.path") + "WEB-INF/classes/start/packagenewblog.sh";
        String command = "sh " + filePath;
        logger.info("execute restart command:" + command);
        try {
            process = Runtime.getRuntime().exec(command);
            process.waitFor();
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            if (process != null) {
                try {
                    process.destroy();
                } catch (Exception e) {
                    logger.error("", e);
                }
            }
        }
    }
}
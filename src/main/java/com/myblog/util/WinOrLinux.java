package com.myblog.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Zephery
 * @since 2017/10/28 15:16
 * Description:
 */
public class WinOrLinux {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(WinOrLinux.class);

    public static boolean isWin() {
        String os = System.getProperty("os.name");
        return os.toLowerCase().startsWith("win");
    }
}
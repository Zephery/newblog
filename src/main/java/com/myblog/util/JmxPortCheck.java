package com.myblog.util;

import com.google.common.base.Strings;
import com.myblog.common.Config;

import java.net.Socket;

/**
 * @author wenzhihuai
 * @since 2018/5/1 15:11
 */
public class JmxPortCheck {

    private static boolean getURL(String ip, Integer port) {
        Socket client;
        try {
            client = new Socket(ip, port);
            client.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static String check() {
        String ipAndPorts = Config.getProperty("jmx_ip_port");
        if (!Strings.isNullOrEmpty(ipAndPorts)) {
            String[] strings = ipAndPorts.split(",");
            for (String string : strings) {
                String[] str = string.split(":");
                if (getURL(str[0], Integer.parseInt(str[1]))) {
                    return string;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        check();
    }
}
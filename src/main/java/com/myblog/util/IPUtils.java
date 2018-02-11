package com.myblog.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Pattern;


public class IPUtils {

    private static final Logger logger = LoggerFactory.getLogger(IPUtils.class);

    public static String ipRegix = "((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
    public static Pattern ipPattern = Pattern.compile(ipRegix);
    private static long[][] intranet_ip_ranges = new long[][]{{ipToInt("10.0.0.0"), ipToInt("10.255.255.255")},
            {ipToInt("172.16.0.0"), ipToInt("172.31.255.255")},
            {ipToInt("192.168.0.0"), ipToInt("192.168.255.255")}};

    public static boolean isIp(String in) {
        if (in == null) {
            return false;
        }
        return ipPattern.matcher(in).matches();
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.indexOf(",") != -1) {
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * Convert IP to Int.
     *
     * @param addr
     * @param isSegment true IP segment, false full IP.
     * @return
     */
    public static int ipToInt(final String addr, final boolean isSegment) {
        final String[] addressBytes = addr.split("\\.");
        int length = addressBytes.length;
        if (length < 3) {
            return 0;
        }
        int ip = 0;
        try {
            for (int i = 0; i < 3; i++) {
                ip <<= 8;
                ip |= Integer.parseInt(addressBytes[i]);
            }
            ip <<= 8;
            if (isSegment || length == 3) {
                ip |= 0;
            } else {
                ip |= Integer.parseInt(addressBytes[3]);
            }
        } catch (Exception e) {
            logger.warn("Warn ipToInt addr is wrong: addr=" + addr, e);
        }

        return ip;
    }

    /**
     * 将ip转化为数字，并且保持ip的大小顺序不变 如 ipToInt("10.75.0.1") > ipToInt("10.75.0.0")
     * 如果ip不合法则返回 0
     *
     * @return
     */
    public static int ipToInt(final String addr) {
        return ipToInt(addr, false);
    }

    /**
     * 是否为内网ip A类 10.0.0.0-10.255.255.255 B类 172.16.0.0-172.31.255.255 C类
     * 192.168.0.0-192.168.255.255 不包括回环ip
     *
     * @param ip
     * @return
     */
    public static boolean isIntranetIP(String ip) {
        if (!isIp(ip)) {
            return false;
        }
        long ipNum = ipToInt(ip);
        for (long[] range : intranet_ip_ranges) {
            if (ipNum >= range[0] && ipNum <= range[1]) {
                return true;
            }
        }
        return false;
    }

    public static String getAddressByIP(String strIP) {
        try {
            URL url = new URL("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=" + strIP);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GBK"));
            String line = null;
            StringBuffer result = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            JsonParser parser = new JsonParser();
            JsonObject object = parser.parse(result.toString()).getAsJsonObject();
            return object.get("city").toString().replaceAll("\"", "");
        } catch (Exception e) {
            return "Address Error";
        }
    }

    public static String getServerIp() {
        String ip = "";
        String url = "http://icanhazip.com/";
        try {
            ip = HttpHelper.getInstance().get(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ip;
    }

    public static void main(String[] args) {
        String ip = IPUtils.getServerIp().replaceAll("\n", "");
        if ("113.108.186.130".equals(ip)) {
            System.out.println(ip);
        }
    }
}

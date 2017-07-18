package com.myblog.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Zephery on 2017/6/29.
 */
public class TestUtil {
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
            JsonParser parser=new JsonParser();
            JsonObject object=parser.parse(result.toString()).getAsJsonObject();
            return object.get("city").toString();
        } catch (IOException e) {
            return "读取失败";
        }
    }
    public static void main(String args[]){
        System.out.println(getAddressByIP("223.73.168.248"));
    }
}

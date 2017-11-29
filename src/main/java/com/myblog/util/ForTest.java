package com.myblog.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by Zephery on 2017/1/23.
 */
public class ForTest {
    public static void main(String args[]) {
        String str = "callback( {\"client_id\":\"YOUR_APPID\",\"openid\":\"YOUR_OPENID\"} );";
        String json = str.replaceAll("callback\\( ", "").replace(" );", "");
        System.out.println(json);
        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(json).getAsJsonObject();
        System.out.println(object.get("openid").toString().replaceAll("\"", ""));
    }
}

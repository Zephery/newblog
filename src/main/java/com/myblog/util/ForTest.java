package com.myblog.util;

import com.google.gson.JsonParser;

/**
 * Created by Zephery on 2017/1/23.
 */
public class ForTest {
    public static void main(String args[]) {
        String str = "{ \"ret\": 0, \"msg\": \"\", \"is_lost\":0, \"nickname\": \"Twilight\", \"gender\": \"?\", \"province\": \"??\", \"city\": \"??\", \"year\": \"1899\", \"figureurl\": \"http:\\/\\/qzapp.qlogo.cn\\/qzapp\\/101323012\\/30AAE7946E6562029F4EA3968D8C49B3\\/30\", \"figureurl_1\": \"http:\\/\\/qzapp.qlogo.cn\\/qzapp\\/101323012\\/30AAE7946E6562029F4EA3968D8C49B3\\/50\", \"figureurl_2\": \"http:\\/\\/qzapp.qlogo.cn\\/qzapp\\/101323012\\/30AAE7946E6562029F4EA3968D8C49B3\\/100\", \"figureurl_qq_1\": \"http:\\/\\/q.qlogo.cn\\/qqapp\\/101323012\\/30AAE7946E6562029F4EA3968D8C49B3\\/40\", \"figureurl_qq_2\": \"http:\\/\\/q.qlogo.cn\\/qqapp\\/101323012\\/30AAE7946E6562029F4EA3968D8C49B3\\/100\", \"is_yellow_vip\": \"0\", \"vip\": \"0\", \"yellow_vip_level\": \"0\", \"level\": \"0\", \"is_yellow_year_vip\": \"0\" }";
        JsonParser parser = new JsonParser();
        System.out.println(parser.parse(str).toString());
    }
}

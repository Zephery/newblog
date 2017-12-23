package com.myblog.util;

import com.baidu.aip.ocr.AipOcr;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.myblog.common.Common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * @author Zephery
 * @since 2017/12/23 18:43
 */
public class WordRecognition {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(WordRecognition.class);
    //设置APPID/AK/SK
    private static final String APP_ID = Common.BAIDU_APP_ID;
    private static final String API_KEY = Common.BAIDU_APP_KEY;
    private static final String SECRET_KEY = Common.BAIDU_SECRET_KEY;
    private static WordRecognition instance;
    private AipOcr client = null;

    private WordRecognition() {
        try {
            initAipOcr();
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    public synchronized static WordRecognition getInstance() {
        if (instance == null) {
            instance = new WordRecognition();
        }
        return instance;
    }

    public static void main(String[] args) throws Exception {
        WordRecognition.getInstance().recognizeWordByUrl("http://image.wenzhihuai.com/images/20171223071941.png");
    }

    private void initAipOcr() throws Exception {
        client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
    }

    public String recognizeWordByUrl(String url) throws Exception {
        HashMap<String, String> options = new HashMap<>();
        options.put("detect_direction", "true");
        options.put("probability", "true");
        String str = client.basicGeneralUrl(url, options).toString();
        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(str).getAsJsonObject();
        try {
            return object.get("words_result").getAsJsonArray().get(0).getAsJsonObject().get("words").getAsString();
        } catch (Exception e) {
            logger.error("", e);
            throw new Exception();
        }
    }
}
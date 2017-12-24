package com.myblog.util;

import com.baidu.aip.ocr.AipOcr;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.myblog.common.Common;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.UUID;

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
        WordRecognition.getInstance().recognizeImagePath("http://login.sina.com.cn/cgi/pin.php");
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
        logger.info("返回的文本：" + str);
        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(str).getAsJsonObject();
        try {
            return object.get("words_result").getAsJsonArray().get(0).getAsJsonObject().get("words").getAsString();
        } catch (Exception e) {
            logger.error("", e);
            throw new Exception();
        }
    }

    public String recognizeImagePath(String imageUrl) throws Exception {
        String filePath = urltoImagePath(imageUrl);
        HashMap<String, String> options = new HashMap<>();
        options.put("detect_direction", "true");
        options.put("probability", "true");
        String str = client.basicGeneral(filePath, options).toString();
        logger.info("返回的文本：" + str);
        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(str).getAsJsonObject();
        try {
            return object.get("words_result").getAsJsonArray().get(0).getAsJsonObject().get("words").getAsString();
        } catch (Exception e) {
            logger.error("", e);
            throw new Exception();
        } finally {
            FileUtils.deleteQuietly(new File(filePath));
        }
    }


    private String urltoImagePath(String imageUrl) throws Exception {
        String filePath = System.getProperty("myblog.path") + UUID.randomUUID() + ".jpg";
        logger.info("captchPath:" + filePath);
        try {
            File file = new File(filePath);
            OutputStream os = new FileOutputStream(file);
            //创建一个url对象
            URL url = new URL(imageUrl);
            InputStream is = url.openStream();
            byte[] buff = new byte[1024];
            while (true) {
                int readed = is.read(buff);
                if (readed == -1) {
                    break;
                }
                byte[] temp = new byte[readed];
                System.arraycopy(buff, 0, temp, 0, readed);
                //写入文件
                os.write(temp);
            }
            is.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }
}
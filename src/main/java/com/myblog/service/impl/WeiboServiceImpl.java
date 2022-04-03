//package com.myblog.service.impl;
//
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import com.myblog.common.Config;
//import com.myblog.dao.WeiboMapper;
//import com.myblog.service.IWeiboService;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.http.HttpEntity;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.protocol.HttpClientContext;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.io.IOException;
//import java.net.URLEncoder;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * Created with IntelliJ IDEA.
// * User: Zephery
// * Time: 2017/8/20 0:05
// * Description:微博nlp
// */
//@Service("weiboService")
//public class WeiboServiceImpl implements IWeiboService {
//    //logger
//    private static final Logger logger = LoggerFactory.getLogger(WeiboServiceImpl.class);
//    private static final Map<Integer, String> TYPE = new ConcurrentHashMap<>();
//    private static final CloseableHttpClient httpClient = HttpClients
//            .custom()
//            .setDefaultRequestConfig(
//                    RequestConfig.custom()
//                            .build()).build();
//
//    static {
//        TYPE.put(1, "正向");
//        TYPE.put(2, "负向");
//        TYPE.put(3, "客观");
//    }
//
//    @Resource
//    private WeiboMapper weiboMapper;
//
//
//    @Override
//    public JsonObject getWeiboDetail(String sentence) {
//        JsonObject object = null;
//        CloseableHttpResponse response = null;
//        try {
//            HttpClientContext context = HttpClientContext.create();
//            String weiboflaskurl = Config.getProperty("weiboflask.url");
//            HttpGet httpGet = new HttpGet(weiboflaskurl + URLEncoder.encode(sentence.replaceAll("/", ""), "utf-8"));
//            response = httpClient.execute(httpGet, context);
//            HttpEntity entity = response.getEntity();
//            JsonParser parser = new JsonParser();
//            String str = EntityUtils.toString(entity);
//            object = parser.parse(str).getAsJsonObject();
//            if (StringUtils.isNotEmpty(object.get("type").toString())) {
//                object.addProperty("type", TYPE.get(Integer.parseInt(object.get("type").toString())));
//            }
//            response.close();
//        } catch (IOException e) {
//            logger.error("", e);
//        } finally {
//            if (response != null) {
//                try {
//                    response.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return object;
//    }
//}
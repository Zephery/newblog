package com.myblog.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.myblog.dao.WeiboMapper;
import com.myblog.model.Weibo;
import com.myblog.service.IWeiboService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/8/20 0:05
 * Description:
 */
@Service("weiboService")
public class WeiboServiceImpl implements IWeiboService {
    @Resource
    private WeiboMapper weiboMapper;
    //logger
    private static final Logger logger = LoggerFactory.getLogger(WeiboServiceImpl.class);
    private static final Map<Integer, String> TYPE = new ConcurrentHashMap<>();
    private static final CloseableHttpClient httpClient = HttpClients
            .custom()
            .setDefaultRequestConfig(
                    RequestConfig.custom()
                            .setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY)
                            .build()).build();

    static {
        TYPE.put(1, "正向");
        TYPE.put(2, "负向");
        TYPE.put(3, "客观");
    }

    @Override
    public List<Weibo> getAllWeibo() {
        List<Weibo> weibos = weiboMapper.getAllWeibo();
        for (Weibo weibo : weibos) {
            try {
                weibo.setTypename(TYPE.get(weibo.getType()));
            } catch (Exception e) {
                logger.error("分类类型错误", e);
            }
        }
        return weibos;
    }

    @Override
    public JsonObject getWeiboDetail(String sentence) {
        JsonObject object = null;
        try {
            CloseableHttpResponse response = null;
            HttpClientContext context = HttpClientContext.create();
            HttpGet httpGet = new HttpGet("http://127.0.0.1:5000/helloscore/" + sentence);
            response = httpClient.execute(httpGet, context);
            HttpEntity entity = response.getEntity();
            JsonParser parser = new JsonParser();
            object = parser.parse(EntityUtils.toString(entity)).getAsJsonObject();
            if (StringUtils.isNotEmpty(object.get("type").toString())) {
                object.addProperty("type", TYPE.get(Integer.parseInt(object.get("type").toString())));
            }
        } catch (IOException e) {
            logger.error("", e);
        }
        return object;
    }
}
package com.myblog.util;

import com.myblog.common.Config;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/10/9 16:37
 * Description:广州图书馆借书记录抓取
 */
public class LibraryUtil {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(LibraryUtil.class);
    //HttpClient 超时配置
    private RequestConfig globalConfig = RequestConfig.custom()
            .setCookieSpec(CookieSpecs.STANDARD)
            .setConnectionRequestTimeout(6000)
            .setConnectTimeout(6000).build();
    private CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
    private HttpPost httpPost = null;

    public static void test() {
        String username = Config.getProperty("guangtu.username");
        String password = Config.getProperty("guagntu.password");
        LibraryUtil libraryUtil = new LibraryUtil();
        String httpOrgCreateTest = "http://login.gzlib.gov.cn/sso-server/login";
        Map<String, String> createMap = new HashMap<String, String>();
        createMap.put("username", username);
        createMap.put("password", password);
        String httpOrgCreateTestRtn = libraryUtil.doPost(httpOrgCreateTest, createMap, "utf-8");
        String newurl = "http://login.gzlib.gov.cn" + Jsoup.parse(httpOrgCreateTestRtn).select("form").attr("action");
        System.out.println(newurl);
        String two = libraryUtil.doPost(newurl, createMap, "utf-8");
        System.out.println(two);
    }

    public static void main(String[] args) {
        test();
    }

    public String doPost(String url, Map<String, String> map, String charset) {
        String result = null;
        try {
            httpPost = new HttpPost(url);
            httpPost.addHeader("refer", "http://www.gzlib.gov.cn/");
            //设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> elem = (Map.Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            logger.error("抓取出错", ex);
        }
        return result;
    }
}
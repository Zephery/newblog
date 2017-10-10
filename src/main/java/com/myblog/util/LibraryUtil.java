package com.myblog.util;

import com.myblog.common.Config;
import net.sf.json.JSONObject;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/10/9 16:37
 * Description:广州图书馆借书记录抓取
 * 参考来源：http://blog.csdn.net/zmx729618/article/details/51801958
 */
public class LibraryUtil {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(LibraryUtil.class);
    private static CloseableHttpClient httpClient = null;
    private static HttpClientContext context = null;
    private static CookieStore cookieStore = null;
    private static RequestConfig requestConfig = null;

    static {
        init();
    }

    private static void init() {
        context = HttpClientContext.create();
        cookieStore = new BasicCookieStore();
        // 配置超时时间（连接服务端超时1秒，请求数据返回超时2秒）
        requestConfig = RequestConfig.custom().setConnectTimeout(1200000).setSocketTimeout(600000)
                .setConnectionRequestTimeout(600000).build();
        // 设置默认跳转以及存储cookie
        httpClient = HttpClientBuilder.create()
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setRedirectStrategy(new DefaultRedirectStrategy()).setDefaultRequestConfig(requestConfig)
                .setDefaultCookieStore(cookieStore).build();
    }

    /**
     * http get
     *
     * @param url
     * @return response
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static CloseableHttpResponse get(String url, Header[] header) throws IOException {
        HttpGet httpget = new HttpGet(url);
        if (header != null && header.length > 0) {
            httpget.setHeaders(header);
        }
        CloseableHttpResponse response = httpClient.execute(httpget, context);
        try {
            cookieStore = context.getCookieStore();
            List<Cookie> cookies = cookieStore.getCookies();
            for (Cookie cookie : cookies) {
                System.out.println("key:" + cookie.getName() + "  value:" + cookie.getValue());
            }
        } finally {
            //注释掉下面，不然不能打印链接内容
//            response.close();
        }
        return response;
    }

    /**
     * http post
     *
     * @param url
     * @param parameters form表单
     * @return response
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static CloseableHttpResponse post(String url, String parameters, Header[] headers)
            throws IOException {
        System.out.println(parameters);
        HttpPost httpPost = new HttpPost(url);
        if (headers != null && headers.length > 0) {
            for (Header header : headers) {
                httpPost.addHeader(header);
            }
        }
        System.out.println("................................................");
        StringEntity entity = new StringEntity(parameters, "utf-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(httpPost, context);
        try {
            cookieStore = context.getCookieStore();
            List<Cookie> cookies = cookieStore.getCookies();
            for (Cookie cookie : cookies) {
                System.out.println("key:" + cookie.getName() + "  value:" + cookie.getValue());
            }
        } finally {
//            response.close();
        }
        return response;
    }

    public static void printCookies() {
        System.out.println("cookie:");
        cookieStore = context.getCookieStore();
        if (cookieStore == null) {
            return;
        }
        List<Cookie> cookies = cookieStore.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println("key:" + cookie.getName() + "  value:" + cookie.getValue());
        }
    }

    @SuppressWarnings("unused")
    private static List<NameValuePair> toNameValuePairList(String parameters) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        String[] paramList = parameters.split("&");
        for (String parm : paramList) {
            int index = -1;
            for (int i = 0; i < parm.length(); i++) {
                index = parm.indexOf("=");
                break;
            }
            String key = parm.substring(0, index);
            String value = parm.substring(++index, parm.length());
            nvps.add(new BasicNameValuePair(key, value));
        }
        return nvps;
    }

    /**
     * 把结果console出来
     *
     * @param httpResponse
     * @throws ParseException
     * @throws IOException
     */
    public static void printResponse(CloseableHttpResponse httpResponse) throws ParseException, IOException {
        // 获取响应消息实体
        HttpEntity entity = httpResponse.getEntity();
        // 响应状态
        System.out.println("status:" + httpResponse.getStatusLine());
        System.out.println("headers:");
        HeaderIterator iterator = httpResponse.headerIterator();
        while (iterator.hasNext()) {
            System.out.println("\t" + iterator.next());
        }
        // 判断响应实体是否为空
        if (entity != null) {
            String responseString = EntityUtils.toString(entity);
            //      System.out.println("response length:" + responseString.length());
            System.out.println("response content:" + responseString.replace("\r\n", ""));
        }
        System.out.println(
                "------------------------------------------------------------------------------------------\r\n");
        httpResponse.close();
    }

    /**
     * 直接把Response内的Entity内容转换成String
     *
     * @param httpResponse
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public static String toString(CloseableHttpResponse httpResponse) throws ParseException, IOException {
        // 获取响应消息实体
        try {
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null)
                return EntityUtils.toString(entity);
            else
                return null;
        } finally {
            httpResponse.close();
        }
    }

    /**
     * 手动增加cookie
     *
     * @param name
     * @param value
     * @param domain
     * @param path
     */
    public static void addCookie(String name, String value, String domain, String path) {
        BasicClientCookie cookie = new BasicClientCookie(name, value);
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookieStore.addCookie(cookie);
    }

    public static void main(String[] args) {
        new LibraryUtil().test();
    }

    private void test() {
        String username = Config.getProperty("guangtu.username");
        String password = Config.getProperty("guangtu.password");
        try {
            CloseableHttpResponse re = get("http://www.gzlib.gov.cn/", null);
            System.out.println(re.getHeaders("JSESSIONID"));
            String loginURL = "http://login.gzlib.gov.cn/sso-server/login?service=http%3A%2F%2Fwww.gzlib.gov.cn%2Flogin.jspx%3FreturnUrl%3Dhttp%253A%252F%252Fwww.gzlib.gov.cn%252F%26locale%3Dzh_CN&appId=www.gzlib.gov.cn&locale=zh_CN";
            Header[] headers = {
                    new BasicHeader("Referer", "http://www.gzlib.gov.cn/member/historyLoanList.jspx"),
                    new BasicHeader("origin", "http://login.gzlib.gov.cn"),
                    new BasicHeader("Content-Type", "application/x-www-form-urlencoded"),
                    new BasicHeader("host", "www.gzlib.gov.cn"),
                    new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36")
            };
            CloseableHttpResponse response = get(loginURL, headers);
            String content = toString(response);
            //参考：http://blog.csdn.net/championhengyi/article/details/68491306
            String lt = Jsoup.parse(content).select("form").select("input[name=lt]").attr("value");
            String postdata = "{\"username\": \"440881199504034816\",\n" +
                    "            \"password\": \"034816\",\n" +
                    "            \"_eventId\": \"submit\",\n" +
                    "            \"lt\": \"" + lt + "\"\n" +
                    "            }";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", username);
            jsonObject.put("password", password);
            jsonObject.put("_eventId", "submit");
            jsonObject.put("lt", lt);
            Header[] newheaders = {
                    new BasicHeader("Referer", "http://www.gzlib.gov.cn/member/historyLoanList.jspx"),
                    new BasicHeader("origin", "http://login.gzlib.gov.cn"),
                    new BasicHeader("Content-Type", "application/x-www-form-urlencoded"),
                    new BasicHeader("host", "www.gzlib.gov.cn"),
                    new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36")
            };
            CloseableHttpResponse response1 = post(loginURL, jsonObject.toString(), newheaders);
            printResponse(response1);
//            CloseableHttpResponse response2 = get("http://www.gzlib.gov.cn/member/historyLoanList.jspx", null);
//            printResponse(response2);
        } catch (IOException e) {
            logger.error("错误", e);
        }
    }
}
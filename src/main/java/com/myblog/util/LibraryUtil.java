//package com.myblog.util;
//
//import com.myblog.common.Config;
//import com.myblog.model.Myreading;
//import org.apache.commons.lang.StringUtils;
//import org.apache.http.*;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.CookieStore;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.protocol.HttpClientContext;
//import org.apache.http.cookie.Cookie;
//import org.apache.http.impl.client.*;
//import org.apache.http.impl.cookie.BasicClientCookie;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created with IntelliJ IDEA.
// * User: Zephery
// * Time: 2017/10/9 16:37
// * Description:广州图书馆借书记录抓取
// * 参考来源：http://blog.csdn.net/zmx729618/article/details/51801958
// */
//public class LibraryUtil {
//    //logger
//    private static final Logger logger = LoggerFactory.getLogger(LibraryUtil.class);
//    private static CloseableHttpClient httpClient = null;
//    private static HttpClientContext context = null;
//    private static CookieStore cookieStore = null;
//
//    static {
//        init();
//    }
//
//    private static void init() {
//        context = HttpClientContext.create();
//        cookieStore = new BasicCookieStore();
//        // 配置超时时间（连接服务端超时1秒，请求数据返回超时2秒）
//        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(120000).setSocketTimeout(60000)
//                .setConnectionRequestTimeout(60000).build();
//        // 设置默认跳转以及存储cookie
//        httpClient = HttpClientBuilder.create()
//                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
//                .setRedirectStrategy(new DefaultRedirectStrategy()).setDefaultRequestConfig(requestConfig)
//                .setDefaultCookieStore(cookieStore).build();
//    }
//
//    /**
//     * http get
//     *
//     * @param url
//     * @return response
//     * @throws ClientProtocolException
//     * @throws IOException
//     */
//    public static CloseableHttpResponse get(String url, Header[] header) throws IOException {
//        HttpGet httpget = new HttpGet(url);
//        if (header != null && header.length > 0) {
//            httpget.setHeaders(header);
//        }
//        CloseableHttpResponse response = httpClient.execute(httpget, context);
//        return response;
//    }
//
//    /**
//     * http post
//     *
//     * @param url
//     * @param parameters form表单
//     * @return response
//     * @throws ClientProtocolException
//     * @throws IOException
//     */
//    public static CloseableHttpResponse postParam(String url, String parameters, Header[] headers)
//            throws IOException {
//        System.out.println(parameters);
//        HttpPost httpPost = new HttpPost(url);
//        if (headers != null && headers.length > 0) {
//            for (Header header : headers) {
//                httpPost.addHeader(header);
//            }
//        }
//        List<NameValuePair> nvps = toNameValuePairList(parameters);
//        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
//        CloseableHttpResponse response = httpClient.execute(httpPost, context);
//        return response;
//    }
//
//    public static void printCookies() {
//        cookieStore = context.getCookieStore();
//        if (cookieStore == null) {
//            return;
//        }
//        List<Cookie> cookies = cookieStore.getCookies();
//        for (Cookie cookie : cookies) {
//            System.out.println("key:" + cookie.getName() + "  value:" + cookie.getValue() + " domain:" + cookie.getDomain() + cookie.getPath());
//        }
//    }
//
//    public static String cookiegetSession() {
//        System.out.println("cookie:");
//        cookieStore = context.getCookieStore();
//        if (cookieStore == null) {
//            return null;
//        }
//        List<Cookie> cookies = cookieStore.getCookies();
//        return cookies.get(0).getValue();
//    }
//
//    @SuppressWarnings("unused")
//    private static List<NameValuePair> toNameValuePairList(String parameters) {
//        List<NameValuePair> nvps = new ArrayList<>();
//        String[] paramList = parameters.split("&");
//        for (String parm : paramList) {
//            int index = -1;
//            for (int i = 0; i < parm.length(); i++) {
//                index = parm.indexOf("=");
//                break;
//            }
//            String key = parm.substring(0, index);
//            String value = parm.substring(++index, parm.length());
//            nvps.add(new BasicNameValuePair(key, value));
//        }
//        return nvps;
//    }
//
//    /**
//     * 把结果console出来
//     *
//     * @param httpResponse
//     * @throws ParseException
//     * @throws IOException
//     */
//    public static String printResponse(CloseableHttpResponse httpResponse) throws ParseException, IOException {
//        try {
//            // 获取响应消息实体
//            HttpEntity entity = httpResponse.getEntity();
//            // 响应状态
//            System.out.println("status:" + httpResponse.getStatusLine());
//            System.out.println("headers:");
//            HeaderIterator iterator = httpResponse.headerIterator();
//            while (iterator.hasNext()) {
//                System.out.println("\t" + iterator.next());
//            }
//            String responseString = null;
//            // 判断响应实体是否为空
//            if (entity != null) {
//                responseString = EntityUtils.toString(entity);
//                //      System.out.println("response length:" + responseString.length());
////                System.out.println("response content:" + responseString.replace("\r\n", ""));
//            }
//            return responseString;
//        } catch (IOException e) {
//            logger.error("", e);
//        } finally {
//            if (httpResponse != null) {
//                try {
//                    httpResponse.close();
//                } catch (Exception e) {
//                    logger.error("", e);
//                }
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 直接把Response内的Entity内容转换成String
//     *
//     * @param httpResponse
//     * @return
//     * @throws ParseException
//     * @throws IOException
//     */
//    public static String toString(CloseableHttpResponse httpResponse) throws ParseException, IOException {
//        // 获取响应消息实体
//        try {
//            HttpEntity entity = httpResponse.getEntity();
//            if (entity != null)
//                return EntityUtils.toString(entity);
//            else
//                return null;
//        } finally {
//            httpResponse.close();
//        }
//    }
//
//    /**
//     * 手动增加cookie
//     *
//     * @param name
//     * @param value
//     * @param domain
//     * @param path
//     */
//    public static void addCookie(String name, String value, String domain, String path) {
//        BasicClientCookie cookie = new BasicClientCookie(name, value);
//        cookie.setDomain(domain);
//        cookie.setPath(path);
//        cookieStore.addCookie(cookie);
//    }
//
//
//    public static void printHTML(CloseableHttpResponse response) throws IOException {
//        Header header = response.getHeaders("Location")[0];
//        CloseableHttpResponse eaa = get(header.getValue(), null);
////        printResponse(eaa);
//    }
//
//    //    @Scheduled(cron = "0/30 * * * * ?")
//    public static void start() {
//        htmltoJavaBean();
//        System.out.println("hello world");
//    }
//
//    /**
//     * 参考：http://blog.csdn.net/championhengyi/article/details/68491306
//     *
//     * @return
//     */
//    public static String getHTML() {
//        String username = Config.getProperty("guangtu.username");
//        String password = Config.getProperty("guangtu.password");
//        String string = null;
//        try {
//            CloseableHttpResponse homeResponse = get("http://www.gzlib.gov.cn/", null);
//            homeResponse.close();
//            String loginURL = "http://login.gzlib.gov.cn/sso-server/login?service=http%3A%2F%2Fwww.gzlib.gov.cn%2Flogin.jspx%3FreturnUrl%3Dhttp%253A%252F%252Fwww.gzlib.gov.cn%252F%26locale%3Dzh_CN&appId=www.gzlib.gov.cn&locale=zh_CN";
//            CloseableHttpResponse loginGetResponse = get(loginURL, null);
//            String content = toString(loginGetResponse);
//            String lt = Jsoup.parse(content).select("form").select("input[name=lt]").attr("value");
//            loginGetResponse.close();
//            printCookies();
//            if (StringUtils.isNotEmpty(lt)) {//如果不为空，说明session失效
//                CloseableHttpResponse loginPostResponse = postParam(loginURL, "username=" + username + "&" + "password=" + password + "&" + "_eventId=submit&" + "lt=" + lt, null);
//                printHTML(loginPostResponse);
//                loginPostResponse.close();
//            }
//            CloseableHttpResponse historyResponse = get("http://www.gzlib.gov.cn/member/historyLoanList.jspx", null);
//            string = printResponse(historyResponse);
//        } catch (IOException e) {
//            logger.error("错误", e);
//        }
//        return string;
//    }
//
//    public static List<Myreading> htmltoJavaBean() {
//        String html = getHTML();
//        Element element = Jsoup.parse(html).select("table.jieyue-table").get(0).select("tbody").get(0);
//        List<Myreading> list = new ArrayList<>();
//        Elements trs = element.select("tr");
//        for (Element tr : trs) {
//            Elements tds = tr.select("td");
//            Myreading myreading = new Myreading();
//            myreading.setTitle(tds.get(1).text());
//            myreading.setAuthor(tds.get(2).text());
//            myreading.setBookindex(tds.get(3).text());
//            list.add(myreading);
//            logger.info("借阅记录抓取成功" + myreading.getTitle());
//        }
//        return list;
//    }
//
//    public static void main(String[] args) {
//        htmltoJavaBean();
//    }
//
//}
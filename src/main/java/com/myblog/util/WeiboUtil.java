//package com.myblog.util;
//
//import com.myblog.common.Config;
//import org.apache.http.client.config.CookieSpecs;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.protocol.HttpClientContext;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import weiboclient4j.StatusService;
//import weiboclient4j.WeiboClient;
//import weiboclient4j.WeiboClientException;
//import weiboclient4j.model.Timeline;
//import weiboclient4j.oauth2.DisplayType;
//import weiboclient4j.oauth2.ResponseType;
//import weiboclient4j.oauth2.SinaWeibo2AccessToken;
//import weiboclient4j.utils.JsonUtils;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.URI;
//import java.util.List;
//
///**
// * Created with IntelliJ IDEA.
// * User: Zephery
// * Time: 2017/8/20 12:29
// * Description:
// */
//public class WeiboUtil {
//    private static final String APP_KEY = Config.getProperty("app_key");
//    private static final String APP_SECRET = Config.getProperty("app_secret");
//    private static final String CALLBACK_URL = Config.getProperty("callback_url");
//    private static final long STATUS_ID = 3436240135184587L;
//    private static final long ANOTHER_STATUS_ID = 3436255091659029L;
//    private static final long UID = 1834561765L;
//    public static String USER_AGENT = Config.getProperty("user_agent");
//    private static final CloseableHttpClient httpClient = HttpClients
//            .custom()
//            .setUserAgent(USER_AGENT)
//            .setDefaultRequestConfig(
//                    RequestConfig.custom()
//                            .setCookieSpec(CookieSpecs.IGNORE_COOKIES)
//                            .build()).build();
//    private WeiboClient client;
//    private BufferedReader in;
//
//    public static void main(String[] args) throws Exception {
//        WeiboClient client = new WeiboClient(APP_KEY, APP_SECRET);
//        WeiboUtil cmd = new WeiboUtil();
//        cmd.setClient(client);
//        cmd.retrieveAccessToken();
//        cmd.retrieveHomeTimeline();
//    }
//
//    public void setClient(WeiboClient client) {
//        this.client = client;
//    }
//
//
//    private void retrieveAccessToken() throws IOException, WeiboClientException {
//        String state = "__MY_STATE__";
//        String authorizationCallback = "https://api.weibo.com/oauth2/authorize";
//        String url = this.client.getAuthorizationUrl(ResponseType.Code, DisplayType.Default, state, authorizationCallback);
//        //httpget
//        CloseableHttpResponse response = null;
//        HttpClientContext context = HttpClientContext.create();
//        HttpGet httpGet = new HttpGet(url);
//        response = httpClient.execute(httpGet, context);
//        // 获取所有的重定向位置
//        List<URI> redirectLocations = context.getRedirectLocations();
//        //end
//        System.out.println("Please visit: " + url);
//        System.out.print("Input code: ");
//        in = new BufferedReader(new InputStreamReader(System.in));
//        String code = in.readLine();
//        String accessTokenCallback = "https://api.weibo.com/oauth2/authorize";
//        SinaWeibo2AccessToken accessToken = this.client.getAccessTokenByCode(code, accessTokenCallback);
//        System.out.println();
//        System.out.println("Access token: " + accessToken.getToken());
//        System.out.println("Uid: " + accessToken.getUid());
//        System.out.println("Expires in: " + accessToken.getExpiresIn());
//        System.out.println("Remind in: " + accessToken.getRemindIn());
//        accessToken = new SinaWeibo2AccessToken(accessToken.getToken());
//        this.client.setAccessToken(accessToken);
//    }
//
//    private void retrieveHomeTimeline() throws IOException, WeiboClientException {
//        StatusService statusService = this.client.getStatusService();
//        Timeline homeTimeline = statusService.getHomeTimeline();
//        System.out.println();
//        System.out.println("Home timeline: " + JsonUtils.writeObjectAsString(homeTimeline));
//    }
//
//    private void weibologin() {
//
//    }
//}
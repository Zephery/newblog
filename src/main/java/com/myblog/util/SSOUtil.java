//package com.myblog.util;
//
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import com.myblog.common.SSOCommon;
//import org.apache.commons.lang3.RandomStringUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * @author Zephery
// * @since 2017/11/17 9:11
// */
//public class SSOUtil {
//    //logger
//    private static final Logger logger = LoggerFactory.getLogger(SSOUtil.class);
//    private static final JsonParser parser = new JsonParser();
//
//    /**
//     * 根据code获取token
//     *
//     * @param code
//     * @return
//     */
//    public static String getQQToken(String code) throws Exception {
//        String toGetToken = "https://graph.qq.com/oauth2.0/token?" +
//                "code=" + code +
//                "&grant_type=authorization_code" +
//                "&client_id=" + SSOCommon.qqAppKey +
//                "&client_secret=" + SSOCommon.qqAppSecret +
//                "&redirect_uri=" + SSOCommon.qqRedirectUri;
//        logger.info(toGetToken);
////        access token
//        String tokeContent = HttpHelper.getInstance().get(toGetToken);
//        if (StringUtils.isEmpty(tokeContent)) {
//            throw new Exception("获取qq token错误");
//        }
//        logger.info(tokeContent);
//        String token = null;
//        try {
//            token = tokeContent.split("&")[0].split("=")[1];
//        } catch (Exception e) {
//            throw new Exception("获取qq token错误");
//        }
//        return token;
//    }
//
//    /**
//     * 根据token获取openid
//     *
//     * @param token
//     * @return
//     */
//    public static String getQQOpenId(String token) throws Exception {
//        //openid
//        //callback( {"client_id":"YOUR_APPID","openid":"YOUR_OPENID"} ); 搜索
//        String openUrl = "https://graph.qq.com/oauth2.0/me?access_token=" + token;
//        String openContent = HttpHelper.getInstance().get(openUrl);
//        String json = openContent.replaceAll("callback\\( ", "").replace(" );", "");
//        logger.info(json);
//        JsonObject object = null;
//        if (json != null) {
//            object = parser.parse(json).getAsJsonObject();
//        }
//        String openid = null;
//        if (object != null && object.get("openid") != null) {
//            openid = object.get("openid").getAsString();
//        }
//        return openid;
//    }
//
//    /**
//     * 根据token和openid获取名字
//     *
//     * @param token
//     * @param openid
//     * @return
//     */
//    public static String getQQCustName(String token, String openid) throws Exception {
//        //userInfo
//        String url = "https://graph.qq.com/user/get_user_info?" +
//                "access_token=" + token +
//                "&oauth_consumer_key=" + SSOCommon.qqAppKey +
//                "&openid=" + openid +
//                "&format=json";
//        String content = HttpHelper.getInstance().get(url);
//        if (content == null) {
//            logger.error("获取个人信息出错");
//            return null;
//        }
//        JsonObject object1 = parser.parse(content).getAsJsonObject();
//        return object1.get("nickname").getAsString();
//    }
//
//    /**
//     * 根据code获取微信token和openid
//     *
//     * @param code
//     * @return
//     */
//    public static JsonObject getWeiXinMessage(String code) throws Exception {
//        JsonParser parser = new JsonParser();
//        logger.info("wechat code:" + code);
//        String toGetToken = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
//                "appid=" + SSOCommon.weixinAppKey +
//                "&secret=" + SSOCommon.weixinAppSecret +
//                "&code=" + code +
//                "&grant_type=authorization_code";
//        logger.info(toGetToken);
//        String tokeContent = HttpHelper.getInstance().get(toGetToken);        //access token
//        logger.info(tokeContent);
//        JsonObject object = null;        //取得token和openid
//        if (tokeContent != null) {
//            object = parser.parse(tokeContent).getAsJsonObject();
//            if (object.get("errcode") != null) {
//                throw new Exception("访问微信出现异常");
//            }
//        }
//        return object;
//    }
//
//    /**
//     * 刷新微信token有效期
//     *
//     * @param refresh_token
//     */
//    public static void refreshWeixinToken(String refresh_token) throws Exception {
//        String refreshURL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?" +
//                "appid=" + SSOCommon.weixinAppKey +
//                "&grant_type=refresh_token" +
//                "&refresh_token=" + refresh_token;
//        String content = HttpHelper.getInstance().get(refreshURL);
//        logger.info("刷新token后", content);
//    }
//
//    /**
//     * 根据access_token和openid获取名字
//     *
//     * @param access_token
//     * @param openid
//     * @return
//     */
//    public static String getWeixinNickName(String access_token, String openid) throws Exception {
//        String userInfoURL = "https://api.weixin.qq.com/sns/userinfo?" +
//                "access_token=" + access_token +
//                "&openid=" + openid;
//        String content = HttpHelper.getInstance().get(userInfoURL);
//        if (StringUtils.isEmpty(content)) {
//            throw new Exception("获取微信名字错误");
//        } else {
//            JsonObject object = parser.parse(content).getAsJsonObject();
//            if (object.get("nickname") == null) {
//                throw new Exception("获取微信名字错误");
//            } else {
//                return object.get("nickname").getAsString();
//            }
//        }
//    }
//
//    public static void main(String[] args) throws Exception {
//        for (int i = 0; i < 10000; i++) {
//            System.out.println(RandomStringUtils.randomAlphanumeric(20));
//        }
//    }
//}
//package com.myblog.util;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * @author Zephery
// * @since 2017/12/18 20:55
// */
//public class HTTPStudy {
//    //logger
//    private static final Logger logger = LoggerFactory.getLogger(HTTPStudy.class);
//    private static final String baseURL = "https://www.baidu.com/s?word=keyword";
//
//    public static void baidu(String keyword) throws Exception {
//        String content = HttpHelper.getInstance().get(baseURL.replaceAll("keyword", keyword));
//        Document jsoup = Jsoup.parse(content);
//        Elements elements = jsoup.getElementsByClass("result");
//        for (Element element : elements) {
//            String str = element.select(".c-showurl").text();
//            if (str.contains("www.wenzhihuai.com")) {
//                String wenzhihuai = element.select(".t").select("a").attr("href");
//                HttpHelper.getInstance().get(wenzhihuai);
//                logger.info("百度->温志怀URL：" + wenzhihuai);
//            }
//        }
//    }
//
//    public static void main(String[] args) throws Exception {
//        baidu("温志怀");
//    }
//}
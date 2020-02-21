//package com.myblog.util;
//
//import com.myblog.client.ESClient;
//import com.myblog.common.Common;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.http.HttpEntity;
//import org.apache.http.StatusLine;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.StringEntity;
//import org.elasticsearch.client.Response;
//
//import java.io.IOException;
//import java.net.URLEncoder;
//import java.util.Collections;
//
///**
// * Created with IntelliJ IDEA.
// * User: Zephery
// * Time: 2018/5/9 23:01
// * Description:
// */
//@Slf4j
//public class IndexUtil {
//    public static void createTemplateRestClient() {
//        try {
//            String templateName = "/templatelogs";
//            String indexName = "<newbloglogs-{now/d{YYYYMMdd}}-000001>";
//            //创建模板
//            String template = Common.TEMPLATE;
//            HttpEntity entity = new StringEntity(template, ContentType.APPLICATION_JSON);
//            Response templateExist = ESClient.getClient().performRequest("HEAD", "/_template" + templateName);
//            if (!"OK".equals(templateExist.getStatusLine().getReasonPhrase())) {
//                StatusLine statusLine = ESClient.getClient().performRequest("PUT", "/_template" + templateName, Collections.emptyMap(), entity).getStatusLine();
//                if (!"OK".equals(statusLine.getReasonPhrase())) {
//                    log.info("创建模板失败");
//                }
//            }
//            //先判断索引是否存在 rest
//            StatusLine head = ESClient.getClient().performRequest("HEAD", "/newbloglogs", Collections.emptyMap()).getStatusLine();
//            if (!"OK".equals(head.getReasonPhrase())) {
//                HttpEntity indexsource = new StringEntity(Common.ALIASES, ContentType.APPLICATION_JSON);
//                StatusLine put = ESClient.getClient().performRequest("PUT", "/" + URLEncoder.encode(indexName, "utf-8"), Collections.emptyMap(), indexsource).getStatusLine();
//                if (!"OK".equals(put.getReasonPhrase())) {
//                    log.info("是否创建成功");
//                } else {
//                    log.info("");
//                }
//            }
//        } catch (Exception e) {
//            log.error("", e);
//        }
//    }
//
//    public static void rollover() throws IOException {
//        HttpEntity entity = new StringEntity(Common.ROLLOVER, ContentType.APPLICATION_JSON);
//        StatusLine rollover = ESClient.getClient().performRequest("POST", "/newbloglogs_write/_rollover", Collections.emptyMap(), entity).getStatusLine();
//        if (!"OK".equals(rollover.getReasonPhrase())) {
//            log.error("rollover error");
//        } else {
//            log.info("rollover success");
//        }
//
//    }
//
//    public static void main(String[] args) throws IOException {
//        rollover();
//    }
//}
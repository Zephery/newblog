package com.myblog.util;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QiniuUtil {
    //自己的七牛
    private static final String ACCESS_KEY = "QN3U7hRV4WYTmNSPJLVGCfuthzwN2MsDnPojtaZ4";
    private static final String SECRET_KEY = "4qqIC6qDc4-KNfSqbG3WOvgSEN8mZx5zEDOsAdo8";
    private static Logger log = LoggerFactory.getLogger(QiniuUtil.class);
    private static UploadManager uploadManager = new UploadManager();

    private static String getToken(String bucket) {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String token = auth.uploadToken(bucket);
        return token;
    }

    public static void main(String[] args) {
// http://images11.app.happyjuzi.com/content/201506/29/x34.png
        boolean isOk = QiniuUtil.putFile("images", "images/66.jpg",
                "src/main/webapp/images/66.jpg");
        System.out.println(isOk);
    }

    public static boolean putFile(String bucket, String key, String filePath) {
        try {
            Response res = uploadManager.put(filePath, key, getToken(bucket));
            if (res.isOK()) {
                return true;
            } else {
                log.error("Upload to qiniu failed;File path: " + filePath + ";Error: " + res.error);
                return false;
            }
        } catch (QiniuException e) {
            e.printStackTrace();
            Response r = e.response;
// 请求失败时简单状态信息
            log.error(r.toString());
            try {
// 响应的文本信息
                log.error(r.bodyString());
            } catch (QiniuException e1) {
                log.error(e1.getMessage());
            }
            return false;
        }
    }

    public static boolean copy(String bucket, String key, String targetBucket, String targetKey) {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth);
        try {
            bucketManager.copy(bucket, key, targetBucket, targetKey);
            return true;
        } catch (QiniuException e) {
// TODO Auto-generated catch block
            log.error(String.format("Copy failed.(from %s/%s to %s/%s", bucket, key, targetBucket, targetKey));
            e.printStackTrace();
            return false;
        }
    }


}

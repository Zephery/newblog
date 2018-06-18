package com.myblog.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.File;

public class QiniuUtil {
    //自己的七牛
    private static Logger log = LoggerFactory.getLogger(QiniuUtil.class);
    public static final Configuration cfg = new Configuration(Zone.zone0());
    //...其他参数参考类注释
    public static final UploadManager uploadManager = new UploadManager(cfg);

    public static String getToken(String bucket) {
        System.out.println("qiniuyun");
        String access_key = "QN3U7hRV4WYTmNSPJLVGCfuthzwN2MsDnPojtaZ4";
        String secret_key = "4qqIC6qDc4-KNfSqbG3WOvgSEN8mZx5zEDOsAdo8";
        if (access_key != null && secret_key != null) {
            Auth auth = Auth.create(access_key, secret_key);
            String token = auth.uploadToken(bucket);
            return token;
        } else {
            return null;
        }

    }

    public static void putFile(String bucket, String key, String filePath) {
        try {
            Response res = uploadManager.put(filePath, key, getToken(bucket));
            if (!res.isOK()) {
                log.error("Upload to qiniu failed;File path: " + filePath + ";Error: " + res.error);
            }
        } catch (QiniuException e) {
            e.printStackTrace();
            Response r = e.response;
            log.error(r.toString());
            try {
                log.error(r.bodyString());
            } catch (QiniuException e1) {
                log.error(e1.getMessage());
            }
        }
    }

    public static void putFile(String bucket, String key, File file) {
        try {
//            Response res = uploadManager.put(filePath, key, getToken(bucket));
            Response res = uploadManager.put(file, key, getToken(bucket));
            if (!res.isOK()) {
                log.error("Upload to qiniu failed;File path: " + file.getPath() + ";Error: " + res.error);
            }
        } catch (QiniuException e) {
            e.printStackTrace();
            Response r = e.response;
            log.error(r.toString());
            try {
                log.error(r.bodyString());
            } catch (QiniuException e1) {
                log.error(e1.getMessage());
            }
        }
    }


    public static void putFileBytes(String bucket, String key, byte[] bytes) {
        try {
//            Response res = uploadManager.put(filePath, key, getToken(bucket));
            Response res = uploadManager.put(bytes, key, getToken(bucket));
            if (!res.isOK()) {
                log.error("Upload to qiniu failed;File path: " + ";Error: " + res.error);
            }
        } catch (QiniuException e) {
            e.printStackTrace();
            Response r = e.response;
            log.error(r.toString());
            try {
                log.error(r.bodyString());
            } catch (QiniuException e1) {
                log.error(e1.getMessage());
            }
        }
    }

    public static void putBytes(String bucket, String key, ByteArrayInputStream byteInputStream) {
        try {
            try {
                Response response = uploadManager.put(byteInputStream, key, getToken(bucket), null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception ex) {
            //ignore
        }
    }
}

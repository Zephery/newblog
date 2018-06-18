package com.myblog.util;

import com.UpYun;

import java.io.File;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2018/3/13 15:55
 * Description:
 */
public class UpYunUtil {
    private static final UpYun upyun = new UpYun("imagewenupyun", "18320323887", "wenzhihuai2017.");

    public static void upload(String localFilePath, String fileName) throws Exception {
        // 例2：采用数据流模式上传文件（节省内存）,自动创建父级目录
        File file = new File(localFilePath);
        upyun.setContentMD5(UpYun.md5(file));
//        boolean result = upyun.writeFile(filePath, file, true);
        boolean result = upyun.writeFile(fileName, file, true);
    }

    public static void uploadFile(File file, String fileName) throws Exception {
        upyun.setContentMD5(UpYun.md5(file));
        boolean result = upyun.writeFile(fileName, file, true);
    }

    public static void uploadFileBytes(byte[] bytes, String fileName) throws Exception {
        upyun.writeFile(fileName, bytes, true);
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100; i++) {
            System.out.println(new Random().nextInt(5));
        }
    }
}
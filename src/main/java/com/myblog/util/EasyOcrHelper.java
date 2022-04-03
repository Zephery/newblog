//package com.myblog.util;
//
//import cn.easyproject.easyocr.EasyOCR;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * @author Zephery
// * @since 2017/12/15 22:55
// */
//public class EasyOcrHelper {
//    //logger
//    private static final Logger logger = LoggerFactory.getLogger(EasyOcrHelper.class);
//
//    public static void re() {
//        EasyOCR e = new EasyOCR("D:\\Program Files (x86)\\Tesseract-OCR\\tesseract.exe");
////直接识别验证码图片内容
//        System.out.println(e.discern(HttpHelper.getInstance().get("http://login.sina.com.cn/cgi/pin.php")));
//    }
//
//    public static void main(String[] args) {
//        re();
//    }
//}
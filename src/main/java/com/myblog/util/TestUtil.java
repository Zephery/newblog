//package com.myblog.util;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * Created by Zephery on 2017/6/29.
// */
//public class TestUtil {
//    //logger
//    private static final Logger logger = LoggerFactory.getLogger(TestUtil.class);
//    private static final Integer CORE = Runtime.getRuntime().availableProcessors();
//
//    public static void main(String[] args) throws Exception {
////        new TestUtil().begin();
//        HttpHelper.getInstance().get("http://www.wenzhihuai.com");
//
//    }
//
//    private void begin() throws Exception {
//        long start = System.currentTimeMillis();
//        ExecutorService es = Executors.newFixedThreadPool(CORE);
//        for (int i = 0; i < 20; i++) {
//            System.out.println(i);
//            es.submit(new Press());
//        }
//        es.shutdown();
//        while (true) {
//            if (es.isTerminated()) {
//                System.out.println("end");
//                break;
//            }
//        }
//        System.out.print("end:");
//        System.out.println(System.currentTimeMillis() - start);
//    }
//
//    class Press implements Runnable {
//        @Override
//        public void run() {
//            HttpHelper.getInstance().get("http://119.23.46.71:8080");
//            System.out.println(Thread.currentThread().getName());
//        }
//    }
//}

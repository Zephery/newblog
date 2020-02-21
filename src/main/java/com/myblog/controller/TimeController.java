//package com.myblog.controller;
//
//import com.myblog.common.Config;
//import com.myblog.lucene.BlogIndex;
//import com.myblog.service.*;
//import com.myblog.util.HTTPStudy;
//import com.myblog.util.IndexUtil;
//import org.apache.curator.framework.CuratorFramework;
//import org.apache.curator.framework.CuratorFrameworkFactory;
//import org.apache.curator.retry.ExponentialBackoffRetry;
//import org.joda.time.DateTime;
//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.concurrent.TimeUnit;
//
///**
// * 定时任务
// *
// * @author Zephery
// * @since 2018/2/10 16:30
// */
//@Controller
//@SuppressWarnings("unchecked")
//public class TimeController {
//    //logger
//    private static final Logger logger = LoggerFactory.getLogger(TimeController.class);
//    @Resource
//    private IBlogService blogService;
//    @Resource
//    private ITagService tagService;
//    @Resource
//    private IAsyncService asyncService;
//    @Resource
//    private IWeiboService weiboService;
//    @Resource
//    private RedisTemplate redisTemplate;
//    @Resource
//    private ILogService logService;
//    private static final CuratorFramework CURATORCLIENT = CuratorFrameworkFactory.builder().connectString("119.23.46.71:2181")
//            .sessionTimeoutMs(1000)
//            .connectionTimeoutMs(1000)
//            .canBeReadOnly(false)
//            .retryPolicy(new ExponentialBackoffRetry(1000, Integer.MAX_VALUE))
//            .defaultData(null)
//            .build();
//    private BlogIndex blogIndex = new BlogIndex();
//    private final static String REGULARIP = Config.getProperty("regulartime.server");
//    @Resource
//    private RedissonClient redissonClient;
//
////    /**
////     * 重启本项目
////     */
////    @RequestMapping("/restart")
////    public void restart() {
////        BashUtil.getInstance().executeRestartProject();
////    }
//
//
//    @Scheduled(cron = "0 0/5 1,3 * * ?")
//    public void baiduwenzhihuai() throws Exception {
//        HTTPStudy.baidu("温志怀");
//        logService.record("baiduwenzhihuai", "baiduwenzhihuai完成");
//    }
//
//
//    @RequestMapping("/testRedisson")
//    public void testReentrantLock(HttpServletResponse response) {
//        RLock lock = redissonClient.getLock("anyLock");
//        try {
//            // 1. 最常见的使用方法
//            //lock.lock();
//            // 2. 支持过期解锁功能,10秒钟以后自动解锁, 无需调用unlock方法手动解锁
//            //lock.lock(10, TimeUnit.SECONDS);
//            // 3. 尝试加锁，最多等待3秒，上锁以后10秒自动解锁
//            boolean res = lock.tryLock(15, 12, TimeUnit.SECONDS);
//            if (res) {    //成功
//                // do your business
//                System.out.println(Thread.currentThread().getName());
//                Thread.sleep(14000);
//                System.out.println("lock");
//                response.getWriter().write(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
//            }
//        } catch (InterruptedException | IOException e) {
//            e.printStackTrace();
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    @Scheduled(cron = "30 56 1 * * ?")
//    @RequestMapping("/rollover")
//    public void index() {
//        String rollover = "rollover" + DateTime.now().toString("yyyyMMdd");
//        RLock lock = redissonClient.getLock("rolloverTempLock");
//        try {
//            lock.lock();
//            Object o = redisTemplate.opsForValue().get(rollover);
//            if (o == null) {
//                redisTemplate.opsForValue().set(rollover, "true");
//                IndexUtil.createTemplateRestClient();
//                IndexUtil.rollover();
//            }
//        } catch (Exception e) {
//            logger.error("", e);
//        } finally {
//            lock.unlock();
//        }
//    }
//}
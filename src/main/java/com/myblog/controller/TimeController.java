package com.myblog.controller;

import com.myblog.common.Config;
import com.myblog.lucene.BlogIndex;
import com.myblog.model.Blog;
import com.myblog.model.Weibo;
import com.myblog.service.*;
import com.myblog.util.*;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.joda.time.DateTime;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务
 *
 * @author Zephery
 * @since 2018/2/10 16:30
 */
@Controller
@SuppressWarnings("unchecked")
public class TimeController {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(TimeController.class);
    @Resource
    private IBlogService blogService;
    @Resource
    private ITagService tagService;
    @Resource
    private IAsyncService asyncService;
    @Resource
    private IWeiboService weiboService;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private MongoTemplate mongoTemplate;
    @Resource
    private ILogService logService;
    private static final CuratorFramework CURATORCLIENT = CuratorFrameworkFactory.builder().connectString("119.23.46.71:2181")
            .sessionTimeoutMs(1000)
            .connectionTimeoutMs(1000)
            .canBeReadOnly(false)
            .retryPolicy(new ExponentialBackoffRetry(1000, Integer.MAX_VALUE))
            .defaultData(null)
            .build();
    private BlogIndex blogIndex = new BlogIndex();
    private final static String REGULARIP = Config.getProperty("regulartime.server");
    @Resource
    private RedissonClient redissonClient;

//    /**
//     * 重启本项目
//     */
//    @RequestMapping("/restart")
//    public void restart() {
//        BashUtil.getInstance().executeRestartProject();
//    }

    /**
     * 每天更新借书记录（由于隐私关系已停掉），刷新一遍Lucene索引记录
     * 由于每次更新都要删除本地索引记录，所以时间必须在项目启动完之后再进行更新
     */
    @Scheduled(cron = "0 30 6 * * * ")
    @RequestMapping("/update")
    public void updateLuceneEverydate() throws Exception {
        List<Blog> blogs = blogService.getAllBlogWithContent();
        blogIndex.refreshlucene(blogs);//刷新博客
        logService.record("blog", "刷新博客完成");
        blogService.ajaxbuild();//刷新自动补全
        logService.record("autocomplete", "刷新自动补全完成");
        logService.record("autocomplete", "刷新自动补全完成");
        asyncService.start();//广州图书馆借书记录
        logger.info("刷新广图借书记录完成");
        List<Weibo> weibos = weiboService.getAllWeiboToday();
        if (weibos == null || weibos.size() > 0) {
            logger.info("微博已经更新过了");
        } else {
            PythonUtil.executeMyWeiBo();
            logger.info("微博更新完成");
        }
        PythonUtil.executeGetBaidu();
        logger.info("百度统计更新完成");
        tagService.updatetag(1);
        logger.info("标签云更新完成");
//        logger.info("restart:项目开始重启");
//        BashUtil.getInstance().executeRestartProject();
    }

    @Scheduled(cron = "0 0/5 1,3 * * ?")
    public void baiduwenzhihuai() throws Exception {
        HTTPStudy.baidu("温志怀");
        logService.record("baiduwenzhihuai", "baiduwenzhihuai完成");
    }

    /**
     * 修改为使用分布式锁
     */
    @Scheduled(cron = "0 36 6 * * * ")
    @RequestMapping("/weibo")
    public void weibo() {
//        String ip = IPUtils.getServerIp().replaceAll("\n", "");
//        if (REGULARIP.equals(ip)) {
//            PythonUtil.executeMyWeiBo();
//            logService.record("weibo", "微博更新完成");
//        }
        RLock lock = redissonClient.getLock("anyLock");
        try {
            lock.lock();
            List<Weibo> weibos = weiboService.getAllWeiboToday();
            if (weibos == null || weibos.size() > 0) {
                logger.info("微博已经更新过了");
            } else {
                PythonUtil.executeMyWeiBo();
                logger.info("微博更新完成");
            }
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            lock.unlock();
        }

    }

    @Scheduled(cron = "0/50 * * * * ?")
    @SuppressWarnings("unchecked")
    public void refreshIndex() throws Exception {
        String ip = IPUtils.getServerIp().replaceAll("\n", "");
        if (REGULARIP.equals(ip)) {
            String content = HttpHelper.getInstance().get("http://119.29.188.224:8080");
            logService.record("refreshIndex", "首页刷新完成");
            JedisUtil.getInstance().set("index", content);
        }
    }


    @RequestMapping("/testRedisson")
    public void testReentrantLock(HttpServletResponse response) {
        RLock lock = redissonClient.getLock("anyLock");
        try {
            // 1. 最常见的使用方法
            //lock.lock();
            // 2. 支持过期解锁功能,10秒钟以后自动解锁, 无需调用unlock方法手动解锁
            //lock.lock(10, TimeUnit.SECONDS);
            // 3. 尝试加锁，最多等待3秒，上锁以后10秒自动解锁
            boolean res = lock.tryLock(15, 12, TimeUnit.SECONDS);
            if (res) {    //成功
                // do your business
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(14000);
                System.out.println("lock");
                response.getWriter().write(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
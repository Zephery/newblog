package com.myblog.controller;

import com.myblog.common.Config;
import com.myblog.lucene.BlogIndex;
import com.myblog.model.Blog;
import com.myblog.model.Weibo;
import com.myblog.service.IAsyncService;
import com.myblog.service.IBlogService;
import com.myblog.service.ITagService;
import com.myblog.service.IWeiboService;
import com.myblog.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 定时任务
 *
 * @author Zephery
 * @since 2018/2/10 16:30
 */
@Controller
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
    private BlogIndex blogIndex = new BlogIndex();
    private final static String REGULARIP= Config.getProperty("regulartime.server");
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
        logger.info("刷新博客完成");
        blogService.ajaxbuild();//刷新自动补全
        logger.info("刷新自动补全完成");
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
    }

    @Scheduled(cron = "0 36 6 * * * ")
    @RequestMapping("/weibo")
    public void weibo() throws Exception {
        String ip = IPUtils.getServerIp().replaceAll("\n", "");
        if (REGULARIP.equals(ip)) {
            PythonUtil.executeMyWeiBo();
            logger.info("微博更新完成");
        }
    }

    @Scheduled(cron = "0/20 * * * * ?")
    @SuppressWarnings("unchecked")
    public void refreshIndex() throws Exception {
        String ip = IPUtils.getServerIp().replaceAll("\n", "");
        if (REGULARIP.equals(ip)) {
            String content = HttpHelper.getInstance().get("http://119.29.188.224:8080");
            redisTemplate.opsForHash().put("log", "refreshIndex", content);
            JedisUtil.getInstance().set("index", content);
            logger.info("更新首页完成");
        }
    }
}
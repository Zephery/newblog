package com.myblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.myblog.common.Config;
import com.myblog.lucene.BlogIndex;
import com.myblog.model.*;
import com.myblog.service.*;
import com.myblog.util.*;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Created by Zephery on 2016/8/5.
 */

@Controller
public class IndexController {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Resource
    private IBlogService blogService;
    @Resource
    private ICategoryService categoryService;
    @Resource
    private ILinksService linksService;
    @Resource
    private IMyReadingService myReadingService;
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

    /**
     * 首页
     *
     * @param typeId
     * @param releaseDateStr
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value = "typeId", required = false) String typeId,
                              @RequestParam(value = "releaseDateStr", required = false) String releaseDateStr,
                              HttpServletRequest request)
            throws Exception {
        String page = request.getParameter("pagenum");
        Integer pagenum;
        if (StringUtils.isEmpty(page)) {
            pagenum = 1;
        } else {
            pagenum = Integer.parseInt(page);
        }
        PageHelper.startPage(pagenum, 15);
        ModelAndView mav = new ModelAndView();
        List<Blog> lists = blogService.getAllBlog();
        List<Blog> banners = blogService.getBanner();
        PageInfo<Blog> blogs = new PageInfo<>(lists);
        Integer startpage, endpage;
        if (blogs.getPages() < 6) {
            startpage = 1;
            endpage = blogs.getPages();
        } else {
            if (pagenum > 3) {
                startpage = blogs.getPageNum() - 3;
                endpage = blogs.getPageNum() + 3 > blogs.getPages() ? blogs.getPages() : blogs.getPageNum() + 3;
            } else {
                startpage = 1;
                endpage = blogs.getPageNum() + 4;
            }
        }
        List<Blog> hotblogs = blogService.getByHits();
        mav.addObject("startpage", startpage);
        mav.addObject("endpage", endpage);
        mav.addObject("hotblogs", hotblogs);
        mav.addObject("blogs", blogs.getList());
        mav.addObject("totalpages", blogs.getPages());
        mav.addObject("pageNum", blogs.getPageNum());
        mav.addObject("banners", banners);
        mav.setViewName("index");
        return mav;
    }

    @RequestMapping("/blogbyhits")
    @ResponseBody
    public void blogbyhits(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            List<Blog> blogbyhits = blogService.getByHits();
            Gson gson = new Gson();
            String temp = gson.toJson(blogbyhits);
            response.getWriter().write(temp);
        } catch (Exception e) {
            response.getWriter().write(e.toString());
        }
    }

    @RequestMapping("/getjsonbycategories")
    @ResponseBody
    public void getbycategoryid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            List<Category> categories = categoryService.getAllCatWithoutLife();
            Gson gson = new Gson();
            String temp = gson.toJson(categories);
            response.getWriter().write(temp);
        } catch (Exception e) {
            response.getWriter().write(e.toString());
        }
    }

    @RequestMapping("/biaoqianyun")
    @ResponseBody
    public void biaoqianyun(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            JedisUtil jedis = JedisUtil.getInstance();
            JsonParser parser = new JsonParser();
            String str = jedis.get("biaoqian");
            JsonArray jsonArray = (JsonArray) parser.parse(str);
            Iterator iterator = jsonArray.iterator();
            List<KeyAndValue> biaoqian = new ArrayList<>();
            while (iterator.hasNext()) {
                Gson gson = new Gson();
                KeyAndValue keyAndValue = gson.fromJson((JsonObject) iterator.next(), KeyAndValue.class);
                biaoqian.add(keyAndValue);
            }
            biaoqian.sort(new Comparator<KeyAndValue>() {
                @Override
                public int compare(KeyAndValue o1, KeyAndValue o2) {
                    Integer a = StringUtil.stringgetint(o1.getValue());
                    Integer b = StringUtil.stringgetint(o2.getValue());
                    return b.compareTo(a);
                }
            });
            Gson gson = new Gson();
            String temp = gson.toJson(biaoqian.size() > 16 ? biaoqian.subList(0, 16) : biaoqian);
            response.getWriter().write(temp);
        } catch (Exception e) {
            response.getWriter().write(e.toString());
        }
    }

    @RequestMapping("/links")
    @ResponseBody
    public void links(HttpServletResponse response) {
        try {
            List<Links> list = linksService.getAllLinks();
            Gson gson = new Gson();
            response.getWriter().write(gson.toJson(list));
        } catch (IOException e) {
            logger.error("友情链接出错", e);
        }
    }

    @RequestMapping("/myreading")
    public void myreading(HttpServletResponse response) {
        try {
            Set<Myreading> set = myReadingService.getAllReading();
            Gson gson = new Gson();
            response.getWriter().write(gson.toJson(set));
        } catch (IOException e) {
            logger.error("我的阅读出错", e);
        }
    }


    @RequestMapping(value = "/lucene")
    public void lucene(HttpServletResponse response) throws Exception {
        List<Blog> blogs = blogService.getAllBlogWithContent();
        blogIndex.refreshlucene(blogs);
        response.getWriter().write("success");
    }

    @RequestMapping(value = "/aboutme")
    public ModelAndView abountme() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("aboutme");
        return mv;
    }

    @RequestMapping(value = "/donate")
    public ModelAndView donate() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("donate");
        return mv;
    }

    @RequestMapping(value = "/404")
    public ModelAndView fourzerofour() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("404");
        return mv;
    }

    @RequestMapping("/checkcookie")
    public ModelAndView checkcookie(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("checkcookie");
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        mv.addObject("cookies", cookies);
        mv.addObject("session", session);
        mv.addObject("request", request);
        return mv;
    }

    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updatetag")
    @ResponseBody
    public void updatetag(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (tagService.updatetag(1) == 1) {
            response.getWriter().write("update success");
        } else {
            response.getWriter().write("update error");
        }
    }


    @RequestMapping("/singleToMany")
    @ResponseBody
    public String singleToMany() throws Exception {
        SingleToMany.getInstance().test();
        return DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
    }

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
        HttpHelper.getInstance().get(Config.getProperty("360"));
        logger.info("360SEO完成");
        HttpHelper.getInstance().get(Config.getProperty("baidu"));
        logger.info("baidu完成");
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

    @RequestMapping("/pythontest")
    @ResponseBody
    public String pythontest() {
        PythonUtil.executeMyWeiBo();
        return "aa";
    }

    /**
     * 处理从QQ到12345网站的单点登录
     *
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String redirect_url = "https://graph.qq.com/oauth2.0/authorize?client_id=101323012&redirect_uri=http://www.wenzhihuai.com/qqlogin.do&response_type=code&state=b5f5c4579383a28085a1b8c7c424eddf&scope=get_user_info,add_topic,add_one_blog,add_album,upload_pic,list_album,add_share,check_page_fans,add_t,add_pic_t,del_t,get_repost_list,get_info,get_other_info,get_fanslist,get_idollist,add_idol,del_ido,get_tenpay_addr";
            response.sendRedirect(redirect_url);
            logger.info("aaa");
        } catch (Exception e) {
            logger.error("调用QQ接口异常！", e);
            e.printStackTrace();
        }
    }

    @RequestMapping("/qqlogin")
    @ResponseBody
    @SuppressWarnings("unchecked")
    public String qqLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String code = request.getParameter("code");
        String toGetToken = "https://graph.qq.com/oauth2.0/token?code=" + code + "&grant_type=authorization_code"
                + "&client_id=101323012&client_secret=8afd8601924d31418ea63a83619b21f8&redirect_uri=http://www.wenzhihuai.com/qqlogin.do";
        logger.info(toGetToken);
        //access token
        String tokeContent = HttpHelper.getInstance().get(toGetToken);
        logger.info(tokeContent);
        String token = tokeContent.split("&")[0].split("=")[1];
        if (redisTemplate.opsForValue().get("savedToken") == null) {
            redisTemplate.opsForValue().set("savedToken", token);
        } else {
            String savedToken = redisTemplate.opsForValue().get("savedToken").toString();
            savedToken += "\n" + token;
            redisTemplate.opsForValue().set("savedToken", savedToken);
        }
        //openid
        //callback( {"client_id":"YOUR_APPID","openid":"YOUR_OPENID"} ); 搜索
        String openUrl = "https://graph.qq.com/oauth2.0/me?access_token=" + token;
        String openContent = HttpHelper.getInstance().get(openUrl);
        String json = openContent.replaceAll("callback\\( ", "").replace(" );", "");
        logger.info(json);
        JsonParser parser = new JsonParser();
        JsonObject object = parser.parse(json).getAsJsonObject();
        String openid = object.get("openid").toString().replaceAll("\"", "");
        //userInfo
        String url = "https://graph.qq.com/user/get_user_info?" +
                "access_token=" + token +
                "&oauth_consumer_key=101323012" +
                "&openid=" + openid +
                "&format=json";
        logger.info(url);
        String content = HttpHelper.getInstance().get(url);
        logger.info("qqlogin message");
        logger.info(content);
        logger.info("qqlogin end");
        redisTemplate.opsForList().leftPush("qqmessage", parser.parse(content).toString());
        String sss = parser.parse(content).toString();
        mongoTemplate.insert(sss, "qqMessage");
        return parser.parse(content).toString();
    }

    /**
     * 跳转到微信登陆页面
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/wechatLogin")
    public void wechatLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String redirect_url = "https://open.weixin.qq.com/connect/qrconnect?" +
                    "appid=" + Config.getProperty("wexin.app.key") +
                    "&redirect_uri=" + Config.getProperty("weixin.app.secret") +
                    "&response_type=code" +
                    "&scope=SCOPE" +
                    "&state=STATE" +
                    "#wechat_redirect";
            response.sendRedirect(redirect_url);
            logger.info("aaa");
        } catch (Exception e) {
            logger.error("调用QQ接口异常！", e);
            e.printStackTrace();
        }
    }

    /**
     * 微信回调
     * https://open.weixin.qq.com/connect/qrconnect?appid=wx9a96b80ccf8dd805&redirect_uri=http%3A%2F%2F858a2ec2.ngrok.io%2F12345_weixinreceive%2Freceive.do&response_type=code&scope=snsapi_login&state=3d6be0a4035d839573b04816624a415e#wechat_redirect
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/wechatReturn")
    @SuppressWarnings("unchecked")
    public String wechatReturn(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String code = request.getParameter("code");
        JsonParser parser = new JsonParser();
        logger.info("wechat code:" + code);
        String toGetToken = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=APPID" +
                "&secret=SECRET" +
                "&code=" + code +
                "&grant_type=authorization_code";
        logger.info(toGetToken);
        //access token
        String tokeContent = HttpHelper.getInstance().get(toGetToken);
//        {
//            "access_token":"ACCESS_TOKEN",
//                "expires_in":7200,
//                "refresh_token":"REFRESH_TOKEN",
//                "openid":"OPENID",
//                "scope":"SCOPE"
//        }
        logger.info(tokeContent);
        //TODO 取得token和openid
        JsonObject object = parser.parse(tokeContent).getAsJsonObject();
        String access_token = object.get("access_token").toString();
        String openid = object.get("openid").toString();
        String toUserInfoURL = "https://api.weixin.qq.com/sns/userinfo?" +
                "access_token=" + access_token +
                "&openid=" + openid;
        String userInfoContent = HttpHelper.getInstance().get(toUserInfoURL);
        JsonObject userInfo = parser.parse(userInfoContent).getAsJsonObject();
//        {
//            "openid":"OPENID",
//                "nickname":"NICKNAME",
//                "sex":1,
//                "province":"PROVINCE",
//                "city":"CITY",
//                "country":"COUNTRY",
//                "headimgurl": "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0",
//                "privilege":[
//            "PRIVILEGE1",
//                    "PRIVILEGE2"
//],
//            "unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL"
//
//        }
        return null;
    }
}
package com.myblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.myblog.common.SSOCommon;
import com.myblog.lucene.BlogIndex;
import com.myblog.model.Blog;
import com.myblog.model.Category;
import com.myblog.model.Links;
import com.myblog.model.Myreading;
import com.myblog.service.IBlogService;
import com.myblog.service.ICategoryService;
import com.myblog.service.ILinksService;
import com.myblog.service.IMyReadingService;
import com.myblog.service.ITagService;
import com.myblog.util.PythonUtil;
import com.myblog.util.SingleToMany;
import com.myblog.util.WordRecognition;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Zephery on 2016/8/5.
 */
@Slf4j
@Controller
public class IndexController {
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
    private RedisTemplate redisTemplate;
    private BlogIndex blogIndex = new BlogIndex();

    /**
     * 首页
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/")
    public ModelAndView first(HttpServletRequest request) throws Exception {
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

    /**
     * 首页
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request) throws Exception {
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
    public List<Blog> blogbyhits(HttpServletResponse response) throws Exception {
        try {
            return blogService.getByHits();
        } catch (Exception e) {
            log.error("", e);
            return new ArrayList<>();
        }
    }

    @GetMapping(value = "/getjsonbycategories", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getbycategoryid() {
        List<Category> categories = categoryService.getAllCatWithoutLife();
        Gson gson = new Gson();
        return gson.toJson(categories);
    }

    @GetMapping(value = "/biaoqianyun", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String biaoqianyun() throws Exception {
        return tagService.getBiaoqian();

    }

    @GetMapping(value = "/links", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String links(HttpServletResponse response) {
        List<Links> list = linksService.getAllLinks();
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @RequestMapping(value = "/myreading", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String myreading() {
        Set<Myreading> set = myReadingService.getAllReading();
        Gson gson = new Gson();
        return gson.toJson(set);
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
        if (session.getAttribute("yourfirstvisit") == null) {
            session.setAttribute("yourfirstvisit", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
        }
        Cookie[] cookies = request.getCookies();
        mv.addObject("cookies", cookies);
        mv.addObject("session", session);
        mv.addObject("request", request);
        return mv;
    }

    @RequestMapping("/addsession")
    public void session(String userName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute("userName", userName + DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
        response.getWriter().write("success");
    }

    @RequestMapping("/session")
    public ModelAndView session(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("WEB-INF/views/session");
        if (request.getSession().getAttribute("userName") != null) {
            String userName = request.getSession().getAttribute("userName").toString();
            mv.addObject("userName", userName);
        }
        return mv;
    }

    /**
     * @param response
     * @return
     */
    @RequestMapping("/updatetag")
    @ResponseBody
    public void updatetag(HttpServletResponse response) throws IOException {
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
    @RequestMapping("/qqLogin")
    public void login(HttpServletResponse response) throws Exception {
        try {
            String redirect_url = "https://graph.qq.com/oauth2.0/authorize?" +
                    "client_id=" + SSOCommon.qqAppKey +
                    "&redirect_uri=" + SSOCommon.qqRedirectUri +
                    "&response_type=code" +
                    "&state=" + RandomStringUtils.randomAlphanumeric(10) +      //设置为简单的随机数
                    "&scope=get_user_info";
            response.sendRedirect(redirect_url);
        } catch (Exception e) {
            log.error("调用QQ接口异常！", e);
        }
    }

    /**
     * 跳转到微信登陆页面
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/weixinLogin")
    public void weixinLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String redirect_url = "https://open.weixin.qq.com/connect/qrconnect?" +
                    "appid=" + SSOCommon.weixinAppKey +
                    "&redirect_uri=" + URLEncoder.encode(SSOCommon.weixinRedirectUri, "utf-8") +
                    "&response_type=code" +
                    "&scope=" + SSOCommon.weixinScope +
                    "&state=" + RandomStringUtils.randomAlphanumeric(10) +      //设置为简单的随机数
                    "#wechat_redirect";
            log.info(redirect_url);
            response.sendRedirect(redirect_url);
        } catch (Exception e) {
            log.error("调用QQ接口异常！", e);
        }
    }


    @RequestMapping(value = "/baidu/word", method = RequestMethod.GET)
    @ResponseBody
    public String baiduword(String url) {
        try {
            return WordRecognition.getInstance().recognizeImagePath(url);
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping("/zhoubao")
    @SuppressWarnings("unchecked")
    public ModelAndView zhoubao() {
        ModelAndView mv = new ModelAndView();
        if (redisTemplate.opsForValue().get("zhoubao") != null) {
            mv.addObject("content", redisTemplate.opsForValue().get("zhoubao").toString());
        }
        mv.setViewName("/zhoubao");
        return mv;
    }

    @RequestMapping("/savezhoubao")
    @SuppressWarnings("unchecked")
    @ResponseBody
    public String savezhoubao(String content) {
        if (StringUtils.isNotEmpty(content)) {
            content = StringUtils.trimToNull(content);
        }
        redisTemplate.opsForValue().set("zhoubao", content);
        return "success";
    }

    @RequestMapping("/readIndex")
    public void readIndex(HttpServletResponse response) throws Exception {
        String content = redisTemplate.opsForValue().get("index").toString();
        response.getWriter().write(content);
    }

    @RequestMapping("getAllUrl")
    @ResponseBody
    public Set<String> getAllUrl(HttpServletRequest request) {
        Set<String> result = new HashSet<>();
        WebApplicationContext wc = (WebApplicationContext) request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        RequestMappingHandlerMapping bean = wc.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean.getHandlerMethods();
        for (RequestMappingInfo rmi : handlerMethods.keySet()) {
            PatternsRequestCondition pc = rmi.getPatternsCondition();
            Set<String> pSet = pc.getPatterns();
            result.addAll(pSet);
        }
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        return result;
    }

}
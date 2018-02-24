package com.myblog.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.myblog.demo.DemoService;
import com.myblog.model.KeyAndValue;
import com.myblog.model.Weibo;
import com.myblog.service.IWeiboService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/8/5 0:29
 * Description:
 */
@Controller
public class InterestController {
    //logger
    private static final Logger logger = LoggerFactory.getLogger(InterestController.class);
    @Resource
    private IWeiboService weiboService;
    @Resource
    private DemoService demoService;

    @RequestMapping("/interest")
    public ModelAndView interest() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("interest");
        return mv;
    }

    @RequestMapping("/weibonlp")
    public ModelAndView weibonlpdetail(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String weibo = request.getParameter("weibo");
        List<Weibo> weibos = weiboService.getAllWeiboToday();
        mv.addObject("weibos", weibos);
        if (StringUtils.isNotEmpty(weibo)) {
            try {
                JsonObject object = weiboService.getWeiboDetail(weibo);
                mv.addObject("type", object.get("type"));
                JsonArray array = object.get("data").getAsJsonArray();
                Gson gson = new Gson();
                List<KeyAndValue> kvlist = new ArrayList<>();
                for (JsonElement element : array) {
                    KeyAndValue keyAndValue = gson.fromJson(element, KeyAndValue.class);
                    kvlist.add(keyAndValue);
                }
                if (kvlist.size() > 0) {
                    kvlist.sort((o1, o2) -> {
                        if (Float.parseFloat(o1.getValue()) > Float.parseFloat(o2.getValue())) {
                            return -1;
                        } else {
                            return 1;
                        }
                    });
                }
                mv.addObject("sentence", weibo);
                mv.addObject("kvs", kvlist);
            } catch (Exception e) {
                logger.error("微博nlp出错", e);
            }
        }
        mv.setViewName("weibonlp");
        return mv;
    }

    @RequestMapping("/sayhello")
    @ResponseBody
    public String sayhello() {
        String hello = demoService.sayHello("hello");
        // show the result
        System.out.println(hello);
        return hello;
    }

    @RequestMapping("/clearcache")
    @CacheEvict(value = "myCache", allEntries = true, beforeInvocation = true)
    public void clearcache(HttpServletResponse response) throws Exception {
        response.getWriter().write("success");
    }
}
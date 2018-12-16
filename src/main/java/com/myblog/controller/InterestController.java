package com.myblog.controller;

import com.myblog.service.IWeiboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        mv.setViewName("weibonlp");
        return mv;
    }

    @RequestMapping("/clearcache")
    @CacheEvict(value = "myCache", allEntries = true, beforeInvocation = true)
    public void clearcache(HttpServletResponse response) throws Exception {
        response.getWriter().write("success");
    }
}
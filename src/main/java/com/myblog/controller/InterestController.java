package com.myblog.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created with IntelliJ IDEA.
 * User: Zephery
 * Time: 2017/8/5 0:29
 * Description:
 */
@Slf4j
@Controller
public class InterestController {

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